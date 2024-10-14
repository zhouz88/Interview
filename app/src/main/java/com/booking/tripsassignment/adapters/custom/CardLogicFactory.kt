package com.booking.tripsassignment.adapters.custom

import android.annotation.SuppressLint
import com.booking.tripsassignment.R
import com.booking.tripsassignment.adapters.BookingsContentAdapter
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_MESSAGE
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_TITLE_UPCOMING
import com.booking.tripsassignment.adapters.BookingsContentAdapter.HeadViewHolder
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
            val builder = StringBuilder()
            builder.append(data.bookingChainbookings[0].hotel.cityName)
            for (i in 1 until data.bookingChainbookings.size) {
                data.bookingChainbookings[i].hotel.cityName.let {
                    if (it != data.bookingChainbookings[i - 1].hotel.cityName) {
                        builder.append(',').append(' ').append(it)
                    }
                }
            }
            binding.dates.text = formatTripDate(data.bookingChainbookings[0].checkin,
                data.bookingChainbookings[data.bookingChainbookings.size - 1].checkout)
            binding.cities.text = "${context.getText(R.string.trip_to_somewhere)} ${
                builder.toString()
            }"
            binding.nights.text = context.getString(R.string.trip_booking_count, "${data.bookingChainbookings.size}")
        }
    }
}