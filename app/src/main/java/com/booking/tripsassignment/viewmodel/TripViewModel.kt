package com.booking.tripsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_TITLE_PAST
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_TITLE_UPCOMING
import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.data.BookingChainInfo
import com.booking.tripsassignment.data.TripContentData
import com.booking.tripsassignment.modules.MockRep
import com.booking.tripsassignment.repository.BookingRepository
import com.booking.tripsassignment.repository.MockNetworkBookingRepository
import com.booking.tripsassignment.utils.LoadingStatus
import com.booking.tripsassignment.utils.Result
import com.booking.tripsassignment.utils.startCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class TripViewModel @Inject constructor(
    @MockRep private val bookingService: BookingRepository
):ViewModel() {
    private val _tripsData: MutableLiveData<LoadingStatus<MutableList<TripContentData>>> by lazy {
        MutableLiveData<LoadingStatus<MutableList<TripContentData>>>()
    }

    val tripsData: LiveData<LoadingStatus<MutableList<TripContentData>>> = _tripsData

    fun fetchData(userId: Int?) {
        startCoroutine({
            _tripsData.value = LoadingStatus.InProgressLoadingStatus(true)
            val result = withContext(Dispatchers.IO) {
                 bookingService.fetchBookings(userId ?: 0).mapOnSuccess { it ->
                     mapBookingsToViewData(it)
                }
            }
            when (result) {
                is Result.Success -> {
                    _tripsData.value = if(result.data?.size == 0) LoadingStatus.ResultEmptyDataStatus(result) else
                        LoadingStatus.ResultSuccessStatus(result)
                }

                is Result.Error -> {
                    _tripsData.value = LoadingStatus.ResultFailureStatus(result)
                }

                else -> {}
            }
        }) {
            // resolve error
            _tripsData.value = LoadingStatus.ResultFailureStatus(Result.Error(it))
        }
    }

    /**
     * Convert the bookings to TripContentDatas for the UI
     */
    private fun mapBookingsToViewData(list: List<Booking>): MutableList<TripContentData> {
        val bookings = list.sortedBy { it.checkin.toDate().time/1000}
        if (bookings.isEmpty()) {
            return mutableListOf<TripContentData>()
        }
        var l = 0
        var r = bookings.size - 1
        val nowtime = LocalDate.now()
        while (l <= r) {
            var mid = (r - l)/2 + l
            if (bookings[mid].checkout.toDate().time <= nowtime.toDate().time) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        // less than r out
        val result = mutableListOf<TripContentData>()
        if (l < bookings.size) {
            result.add(TripContentData.TripHeadItem(HEAD_TITLE_UPCOMING))
            var bookingChain = BookingChainInfo(null, null, mutableListOf(bookings[l]))
            for (i in l + 1.. bookings.size - 1) {
                val lastBooking = bookingChain.bookingChainbookings.last()
                if (bookings[i].checkin == lastBooking.checkout) {
                    bookingChain.bookingChainbookings.add(bookings[i])
                } else {
                    result.add(TripContentData.TripCardItem(bookingChain))
                    bookingChain =
                        BookingChainInfo(null, null, mutableListOf(bookings[i]))
                }
            }
            result.add(TripContentData.TripCardItem(bookingChain))
        }
        if (r >= 0) {
            val result2 = mutableListOf<TripContentData>()
            result.add(TripContentData.TripHeadItem(HEAD_TITLE_PAST))
            var bookingChain = BookingChainInfo(null, null, mutableListOf(bookings[0]))
            for (i in 1 .. r) {
                val lastBooking = bookingChain.bookingChainbookings.last()
                if (bookings[i].checkin == lastBooking.checkout) {
                    bookingChain.bookingChainbookings.add(bookings[i])
                } else {
                    result2.add(TripContentData.TripCardItem(bookingChain))
                    bookingChain =
                        BookingChainInfo(null, null, mutableListOf(bookings[i]))
                }
            }
            result2.add(TripContentData.TripCardItem(bookingChain))
            result.addAll(result2.reversed())
        }
        return result
    }
}