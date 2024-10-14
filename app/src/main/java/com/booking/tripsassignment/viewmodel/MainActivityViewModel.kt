package com.booking.tripsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.data.BookingChainInfo
import com.booking.tripsassignment.data.SectionData
import com.booking.tripsassignment.modules.MockBooking
import com.booking.tripsassignment.repository.BookingRepository
import com.booking.tripsassignment.utils.LoadingStatus
import com.booking.tripsassignment.utils.Result
import com.booking.tripsassignment.utils.startCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel: ViewModel() {
    private val _tripsData: MutableLiveData<LoadingStatus<MutableList<SectionData>>> by lazy {
        MutableLiveData<LoadingStatus<MutableList<SectionData>>>()
    }
    val tripsData: LiveData<LoadingStatus<MutableList<SectionData>>> = _tripsData

    @Inject
    @MockBooking
    lateinit var bookingService: BookingRepository

    fun fetchData(userId: Int?) {
        startCoroutine({
            _tripsData.value = LoadingStatus.InProgressLoadingStatus(true)
            val result = withContext(Dispatchers.IO) {
                 bookingService.fetchBookings(userId ?: 0).mapOnSuccess { it ->
                     val bookings = it.toMutableList()
                     bookings.sortedWith(Comparator { a, b ->
                         a.checkin.compareTo(b.checkin)
                     })
                     if (bookings.isEmpty()) {
                         return@mapOnSuccess mutableListOf<SectionData>()
                     }
                     var l = 0
                     var r = bookings.size - 1
                     val nowtime = LocalDate.now()
                     while (l <= r) {
                         var mid = (r - l)/2 + l
                         if (bookings[mid].checkout <= nowtime) {
                             l = mid + 1
                         } else {
                             r = mid - 1
                         }
                     }
                     // less than r out
                     val result = mutableListOf<SectionData>()
                     if (l < bookings.size) {
                         result.add(getTypeDataFromRaw("upcoming", l, bookings.size - 1, bookings))
                     }
                     if (r >= 0) {
                         result.add(getTypeDataFromRaw("past", 0, r, bookings).apply {
                             trips.reverse()
                         })
                     }
                     return@mapOnSuccess result

                }
            }
            when (result) {
                is Result.Success -> {
                    // changing bookings to Trips 修改 data
                    _tripsData.value = if(result.data?.size == 0) LoadingStatus.ResultEmptyDataStatus(result) else
                        LoadingStatus.ResultSuccessStatus(result)
                }

                is Result.Error -> {
                    // changing error to Trips 修改 data;
                    _tripsData.value = LoadingStatus.ResultFailureStatus(result)
                }

                else -> {}
            }
        }) {
            // resolve error
            _tripsData.value = LoadingStatus.ResultFailureStatus(Result.Error(it))
        }
    }

    private fun getTypeDataFromRaw(type: String, start:Int, end:Int, bookings: MutableList<Booking>) : SectionData{
        val section = SectionData(type, mutableListOf())
        var bookingChain = BookingChainInfo(null, null, mutableListOf(bookings[0]))
        for (i in start..end) {
            val lastBooking = bookingChain.bookingChainbookings.last()
            if (bookings[i].checkin == lastBooking.checkout) {
                bookingChain.bookingChainbookings.add(bookings[i])
            } else {
                section.trips.add(bookingChain)
                bookingChain =
                    BookingChainInfo(null, null, mutableListOf(bookings[i]))
            }
        }
        section.trips.add(bookingChain)
        return section
    }
}