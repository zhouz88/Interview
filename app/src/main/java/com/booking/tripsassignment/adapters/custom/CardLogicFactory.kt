package com.booking.tripsassignment.adapters.custom

import android.annotation.SuppressLint
import com.booking.tripsassignment.R
import com.booking.tripsassignment.adapters.BookingsContentAdapter
import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.data.BookingChainInfo
import com.booking.tripsassignment.data.TripContentData
import com.booking.tripsassignment.utils.ImageLoader
import com.booking.tripsassignment.utils.formatTripDate
import com.chad.library.adapter.base.BaseViewHolder

class CardLogicFactory : AbstractCustomHolderFactory() {
    override fun createLogic(): AbstractCustomHolderLogic? {
        return Logic()
    }

    inner class Logic : AbstractCustomHolderLogic() {
        @SuppressLint("SetTextI18n")
        override fun handleHolderLogic(helper: BaseViewHolder?, item: TripContentData?) {
            val binding = (helper as BookingsContentAdapter.CardViewHolder).binding
            val data = ((item as TripContentData.TripCardItem).data) as BookingChainInfo
            val context = binding.root.context

            ImageLoader.loadImage(binding.tripImage, data.bookingChainbookings[0].hotel.mainPhoto)

            binding.dates.text = formatTripDate(data.bookingChainbookings[0].checkin,
                data.bookingChainbookings[data.bookingChainbookings.size - 1].checkout)

            binding.cities.text = "${context.getText(R.string.trip_to_somewhere)} ${
                convertBookingsToRequiredCities(data.bookingChainbookings)
            }"

            binding.nights.text = if (data.bookingChainbookings.size > 1) context.getString(R.string.trip_booking_count, "${data.bookingChainbookings.size}")
             else context.getString(R.string.trip_booking_count_one, "${data.bookingChainbookings.size}")
        }
    }

    private fun convertBookingsToRequiredCities(bookingChainbookings: MutableList<Booking>): String {
        val builder = StringBuilder()
        builder.append(bookingChainbookings[0].hotel.cityName)
        for (i in 1 until bookingChainbookings.size) {
            bookingChainbookings[i].hotel.cityName.let {
                if (it != bookingChainbookings[i - 1].hotel.cityName) {
                    builder.append(',').append(' ').append(it)
                }
            }
        }
        return builder.toString()
    }
}