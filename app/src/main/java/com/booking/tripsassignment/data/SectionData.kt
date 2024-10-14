package com.booking.tripsassignment.data

import org.joda.time.LocalDate
import java.util.LinkedList

/**
 * A data set containing specific Bookings type, e.g. upcoming, past, etc.
 **/
data class SectionData(
    val type: String ?,
    val trips: MutableList<BookingChainInfo> = mutableListOf()
)



/**
 * startTime:
 * endTime:
 * bookingChainHead:
 * bookingChainTail:
 */
data class BookingChainInfo(
    var startTime: LocalDate?,
    var endTime: LocalDate?,
    val bookingChainbookings: MutableList<Booking> = mutableListOf()
)

