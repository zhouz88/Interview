package com.booking.tripsassignment.data

import org.joda.time.LocalDate


/**
 * item data of the recyclerview for showing the trips
 */
sealed class TripContentData {
    class TripHeadItem(val data: Any): TripContentData()
    class TripCardItem(val data: Any): TripContentData()
}



/**
 * A BookingChainInfo containing one consecutive list of bookings
 */
data class BookingChainInfo(
    var startTime: LocalDate?,
    var endTime: LocalDate?,
    val bookingChainbookings: MutableList<Booking> = mutableListOf()
)

