package com.booking.tripsassignment.data

import org.joda.time.LocalDate
import java.math.BigDecimal

/**
 * A data class that holds details about a Booking (Reservation).
 *
 * @property id: String – A unique ID for each booking
 * @property hotel: [Hotel] – Hotel object where the booking is made.
 * @property checkin: [LocalDate] - Check in date of the booking.
 * @property checkout: [LocalDate] - Check out date of the booking.
 * @property price: [Price] - Price object that refers to the price of the booking.
 *
 * Note: checkout date will always be greater than checkin date.
 */
data class Booking(
    val id: String,
    val hotel: Hotel,
    val checkin: LocalDate,
    val checkout: LocalDate,
    val price: Price
)

/**
 * Data class to hold details about a Hotel.
 *
 * @property id: String - A unique ID for each hotel.
 * @property name: String - Name of the hotel.
 * @property mainPhoto: String - URL for an image of the hotel.
 * @property cityName: String - Name of the city where the hotel is located.
 * @property cityId: String - ID of the city where the hotel is located.
 *
 * Note: cityId is unique for each city.
 *       For consistency, please assume that if the two hotels have same cityId, they will have the same cityName as well.
 */
data class Hotel(
    val id: String,
    val name: String,
    val mainPhoto: String,
    val cityName: String,
    val cityId: Int
)

/**
 * Data class that holds details about the price of a reservation.
 *
 * @property currency: String - Currency of the booking price.
 * @property amount: BigDecimal - Amount of the booking price.
 * @property scale: Int - Ignore.
 */
data class Price(
    val currency: String,
    val amount: BigDecimal,
    val scale: Int
) {
    private constructor(currency: String, amount: Int, scale: Int): this(currency, BigDecimal(amount), scale)

    companion object {
        val mocksEUR = listOf(
            Price(currency = "EUR", amount = 30300, scale = 100),
            Price(currency = "EUR", amount = 102000, scale = 100),
            Price(currency = "EUR", amount = 92350, scale = 100),
            Price(currency = "EUR", amount = 678969, scale = 100),
            Price(currency = "EUR", amount = 9999, scale = 100),
            Price(currency = "EUR", amount = 55000, scale = 100),
            Price(currency = "EUR", amount = 67850, scale = 100),
            Price(currency = "EUR", amount = 4398, scale = 100),
            Price(currency = "EUR", amount = 2000, scale = 100),
            Price(currency = "EUR", amount = 43500, scale = 100),
            Price(currency = "EUR", amount = 205000, scale = 100),
            Price(currency = "EUR", amount = 149800, scale = 100),
            Price(currency = "EUR", amount = 69900, scale = 100),
            Price(currency = "EUR", amount = 89900, scale = 100),
        )

        val mocksINR = listOf(
            Price(currency = "INR", amount = 20300, scale = 100),
            Price(currency = "INR", amount = 112000, scale = 100),
            Price(currency = "INR", amount = 91350, scale = 100),
            Price(currency = "INR", amount = 478950, scale = 100),
            Price(currency = "INR", amount = 4999, scale = 100),
            Price(currency = "INR", amount = 15000, scale = 100),
            Price(currency = "INR", amount = 27850, scale = 100),
            Price(currency = "INR", amount = 9398, scale = 100),
            Price(currency = "INR", amount = 8000, scale = 100),
            Price(currency = "INR", amount = 53500, scale = 100),
            Price(currency = "INR", amount = 705000, scale = 100),
            Price(currency = "INR", amount = 449800, scale = 100),
            Price(currency = "INR", amount = 79900, scale = 100),
            Price(currency = "INR", amount = 99900, scale = 100)
        )
    }
}

data class Booker(
    val id: Int,
    val firstName: String,
    val lastName: String
)