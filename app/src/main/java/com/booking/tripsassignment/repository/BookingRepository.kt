package com.booking.tripsassignment.repository

import android.os.Looper
import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.utils.NetworkError
import com.booking.tripsassignment.utils.Result
import java.lang.RuntimeException
import kotlin.random.Random

/**
 * An interface to represent a repository that returns a list of bookings for the given user.
 */
interface BookingRepository {
    /**
     * Returns list of bookings for a given user Id.
     *
     * Assumptions:
     *
     *  - All the bookings returned will belong to that user.
     */
    fun fetchBookings(userId: Int): Result<List<Booking>>
}

/**
 *  An implementation for the repository which returns bookings based on a generated dataset.
 *  Check [TestCase] class to refer to the different scenarios and which userId will provide bookings for those scenarios.
 */
class MockNetworkBookingRepository : BookingRepository {

    override fun fetchBookings(userId: Int): Result<List<Booking>> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("fetchBookings called on main thread!")
        }

        Thread.sleep(Random.nextInt(10, 2000).toLong())
        if (Random.nextInt(0, 21) % 10 == 0) {
            return Result.Error(NetworkError("API call error"))
        }

        val bookings = MockDataGenerator.bookingsForUser(userId)  ?: return Result.Error(NetworkError("API call error"))
        return Result.Success(bookings)
    }
}