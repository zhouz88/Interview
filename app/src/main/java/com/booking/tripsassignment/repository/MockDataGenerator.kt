package com.booking.tripsassignment.repository

import com.booking.tripsassignment.data.Booker
import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.data.Hotel
import com.booking.tripsassignment.data.Price
import org.joda.time.LocalDate
import kotlin.random.Random

/**
 * Test cases for different scenarios that we encounter in day to day work.
 * You can use these different test cases to evaluate your algorithm to build booking chains.
 *
 * Each test case has a corresponding bookerId which you can use as userId to get bookings data.
 */
enum class TestCase(val testName: String, val bookerId: Int) {
    NO_BOOKING("No bookings", 99999),
    PAST_BOOKING("1 booking in the past", 178374),
    FUTURE_BOOKING("1 upcoming booking", 7298),

    PAST_BOOKINGS("Multiple bookings in the past", 984567),
    FUTURE_BOOKINGS("Multiple bookings in the future", 9876576),
    FUTURE_AND_PAST_BOOKINGS("Multiple past and upcoming bookings", 5678923),

    PAST_CHAIN("1 chain in the past", 9879402),
    FUTURE_CHAIN("1 chain in the future", 89467),
    PAST_AND_FUTURE_CHAIN("1 chain in the past and 1 upcoming", 899848),

    PAST_CHAINS("Multiple chains in the past", 8956733),
    FUTURE_CHAINS("Multiple chains in the future", 231890),
    PAST_AND_FUTURE_CHAINS("Multiple chains in the past and future", 48098),

    MIX_1("Mix of bookings and chains - 1", 8984747),
    MIX_2("Mix of bookings and chains - 2", 885658),

    POWER_USER("100 bookings", 7984567)
}

object MockDataGenerator {

    fun bookingsForUser(id: Int): List<Booking>? {
        val case = TestCase.values().firstOrNull { it.bookerId == id } ?: return null
        return bookings(case)
    }

    private fun bookings(case: TestCase): List<Booking> {
        val bookings: List<Booking>

        val booker = Booker(id = case.bookerId, "first", "last")

        when (case) {
            TestCase.NO_BOOKING -> bookings = emptyList()
            TestCase.PAST_BOOKING -> {
                bookings = listOf(
                    buildBooking(
                        booker = booker,
                        hotel = City.GOA.hotels()[0],
                        price = Price.mocksINR[0],
                        days = -5,
                        duration = 4
                    )
                )
            }
            TestCase.FUTURE_BOOKING ->
                bookings = listOf(
                    buildBooking(
                        booker = booker,
                        hotel = City.GOA.hotels()[1],
                        price = Price.mocksINR[1],
                        days = 28,
                        duration = 10
                    )
                )

            TestCase.PAST_BOOKINGS -> bookings = pastBookings(booker = booker)
            TestCase.FUTURE_BOOKINGS -> bookings = futureBookings(booker = booker)
            TestCase.FUTURE_AND_PAST_BOOKINGS -> bookings = pastBookings(booker = booker) + futureBookings(booker = booker)
            TestCase.PAST_CHAIN -> bookings = pastChain(booker = booker)
            TestCase.FUTURE_CHAIN -> bookings = futureChain(booker = booker)
            TestCase.PAST_AND_FUTURE_CHAIN -> bookings = pastChain(booker = booker) + futureChain1(booker = booker)
            TestCase.PAST_CHAINS -> bookings = pastChain(booker = booker) + pastChain1(booker = booker)
            TestCase.FUTURE_CHAINS -> bookings = futureChain(booker = booker) + futureChain1(booker = booker)
            TestCase.PAST_AND_FUTURE_CHAINS ->
                bookings =
                    pastChain(booker = booker) + pastChain1(booker = booker) + futureChain(booker = booker) + futureChain1(booker = booker)
            TestCase.MIX_1 ->
                bookings = listOf(
                    buildBooking(
                        booker = booker,
                        hotel = City.NICE.hotels()[0],
                        price = Price.mocksEUR[0],
                        days = -50,
                        duration = 4
                    ),

                    buildBooking(
                        booker = booker,
                        hotel = City.NAPLES.hotels()[1],
                        price = Price.mocksEUR[1],
                        days = -100,
                        duration = 10
                    ),
                    buildBooking(
                        booker = booker,
                        hotel = City.STOCKHOLM.hotels()[0],
                        price = Price.mocksEUR[2],
                        days = 10,
                        duration = 4
                    )
                ) + pastChain(booker = booker) + futureChain1(booker = booker)
            TestCase.MIX_2 ->
                bookings = listOf(
                    buildBooking(
                        booker = booker,
                        hotel = City.NICE.hotels()[0],
                        price = Price.mocksEUR[3],
                        days = -100,
                        duration = 30
                    ),

                    buildBooking(
                        booker = booker,
                        hotel = City.STOCKHOLM.hotels()[0],
                        price = Price.mocksEUR[4],
                        days = 20,
                        duration = 1
                    ),

                    ) + pastChain(booker = booker) + futureChain1(booker = booker)

            TestCase.POWER_USER -> {
                val _bookings = arrayListOf<Booking>()
                var hotels = City.values().filter { it.currency() != "INR" }.flatMap { it.hotels() }.shuffled()
                var prices = Price.mocksEUR
                var start = -700
                (1 until 101).forEach {
                    val hotel = hotels[Random.nextInt(0, hotels.size)]
                    val duration = Random.nextInt(1, 15)
                    val price = prices[Random.nextInt(0, prices.size)]
                    val booking = buildBooking(
                        booker = booker,
                        hotel = hotel,
                        price = price,
                        days = start,
                        duration = duration
                    )
                    _bookings.add(booking)

                    start += duration + Random.nextInt(0, 7)
                    if (it % 66 == 0) {
                        hotels = City.values().filter { it.currency() == "INR" }.flatMap { it.hotels() }.shuffled()
                        prices = Price.mocksINR
                    }
                }

                bookings = _bookings
            }
        }

        return bookings.shuffled()
    }
}

private fun buildBooking(booker: Booker, hotel: Hotel, price: Price, days: Int, duration: Int): Booking {
    val id = "$booker-$hotel-$price-$days-$duration"
    val today = LocalDate.now()

    val checkin = today.plusDays(days)
    val checkout = checkin.plusDays(duration)

    return Booking(
        id = id,
        hotel = hotel,
        checkin = checkin,
        checkout = checkout,
        price = price
    )
}

private fun pastBookings(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel = City.GOA.hotels()[2],
            price = Price.mocksINR[2],
            days = -5,
            duration = 4
        ),

        buildBooking(
            booker = booker,
            hotel = City.STOCKHOLM.hotels()[1],
            price = Price.mocksEUR[3],
            days = -180,
            duration = 1
        ),

        buildBooking(
            booker = booker,
            hotel = City.STOCKHOLM.hotels()[0],
            price = Price.mocksEUR[5],
            days = -365,
            duration = 15
        )
    )
}

private fun futureChain1(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[0],
            price = Price.mocksINR[0],
            days = 83,
            duration = 4
        ),
        buildBooking(
            booker = booker,
            hotel = City.AGRA.hotels()[0],
            price = Price.mocksINR[1],
            days = 87,
            duration = 10
        ),
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[1],
            price = Price.mocksINR[2],
            days = 97,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.GOA.hotels()[0],
            price = Price.mocksINR[3],
            days = 99,
            duration = 3
        ),
        buildBooking(
            booker = booker,
            hotel = City.GOA.hotels()[1],
            price = Price.mocksINR[4],
            days = 102,
            duration = 7
        ),
        buildBooking(
            booker = booker,
            hotel = City.PUNE.hotels()[0],
            price = Price.mocksINR[5],
            days = 109,
            duration = 4
        ),
        buildBooking(
            booker = booker,
            hotel = City.PONDICHERRY.hotels()[0],
            price = Price.mocksINR[6],
            days = 113,
            duration = 7
        ),
        buildBooking(
            booker = booker,
            hotel = City.KOVALAM.hotels()[0],
            price = Price.mocksINR[7],
            days = 120,
            duration = 15
        ),
        buildBooking(
            booker = booker,
            hotel = City.COCHIN.hotels()[0],
            price = Price.mocksINR[0],
            days = 135,
            duration = 9
        ),
        buildBooking(
            booker = booker,
            hotel = City.PUNE.hotels()[1],
            price = Price.mocksINR[1],
            days = 144,
            duration = 1
        ),
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[2],
            price = Price.mocksINR[2],
            days = 145,
            duration = 1
        )
    )
}

private fun futureBookings(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel =
            City.AMALFI.hotels()[0],
            price = Price.mocksEUR[2],
            days = 5,
            duration = 14
        ),

        buildBooking(
            booker = booker,
            hotel = City.AMALFI.hotels()[1],
            price = Price.mocksEUR[3],
            days = 28,
            duration = 1
        ),

        buildBooking(
            booker = booker,
            hotel = City.FLORENCE.hotels()[0],
            price = Price.mocksEUR[4],
            days = 30,
            duration = 15
        ),

        buildBooking(
            booker = booker,
            hotel = City.PARIS.hotels()[0],
            price = Price.mocksEUR[5],
            days = 180,
            duration = 15
        ),

        buildBooking(
            booker = booker,
            hotel = City.KOVALAM.hotels()[0],
            price = Price.mocksINR[6],
            days = 200,
            duration = 30
        ),
    )
}

private fun futureChain(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[0],
            price = Price.mocksEUR[6],
            days = 30,
            duration = 5
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[1],
            price = Price.mocksEUR[7],
            days = 35,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[2],
            price = Price.mocksEUR[8],
            days = 37,
            duration = 4
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[1],
            price = Price.mocksEUR[1],
            days = 41,
            duration = 9
        ),
        buildBooking(
            booker = booker,
            hotel = City.FLORENCE.hotels()[0],
            price = Price.mocksEUR[2],
            days = 50,
            duration = 3
        ),
        buildBooking(
            booker = booker,
            hotel = City.AMALFI.hotels()[0],
            price = Price.mocksEUR[3],
            days = 53,
            duration = 5
        ),
        buildBooking(
            booker = booker,
            hotel = City.AMALFI.hotels()[1],
            price = Price.mocksEUR[4],
            days = 58,
            duration = 15
        )
    )
}

private fun pastChain(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[0],
            price = Price.mocksEUR[6],
            days = -30,
            duration = 5
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[1],
            price = Price.mocksEUR[7],
            days = -25,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[2],
            price = Price.mocksEUR[1],
            days = -23,
            duration = 4
        ),
        buildBooking(
            booker = booker,
            hotel = City.MILAN.hotels()[2],
            price = Price.mocksEUR[2],
            days = -19,
            duration = 9
        ),
        buildBooking(
            booker = booker,
            hotel = City.FLORENCE.hotels()[0],
            price = Price.mocksEUR[3],
            days = -10,
            duration = 3
        ),
        buildBooking(
            booker = booker,
            hotel = City.AMALFI.hotels()[0],
            price = Price.mocksEUR[8],
            days = -7,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.AMALFI.hotels()[1],
            price = Price.mocksEUR[4],
            days = -5,
            duration = 4
        )
    )
}

private fun pastChain1(booker: Booker): List<Booking> {
    return listOf(
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[0],
            price = Price.mocksINR[0],
            days = -100,
            duration = 5
        ),
        buildBooking(
            booker = booker,
            hotel = City.AGRA.hotels()[0],
            price = Price.mocksINR[1],
            days = -95,
            duration = 10
        ),
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[1],
            price = Price.mocksINR[2],
            days = -85,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.GOA.hotels()[0],
            price = Price.mocksINR[3],
            days = -83,
            duration = 10
        ),
        buildBooking(
            booker = booker,
            hotel = City.GOA.hotels()[1],
            price = Price.mocksINR[4],
            days = -73,
            duration = 2
        ),
        buildBooking(
            booker = booker,
            hotel = City.PUNE.hotels()[0],
            price = Price.mocksINR[5],
            days = -71,
            duration = 3
        ),
        buildBooking(
            booker = booker,
            hotel = City.PONDICHERRY.hotels()[0],
            price = Price.mocksINR[6],
            days = -68,
            duration = 4
        ),
        buildBooking(
            booker = booker,
            hotel = City.KOVALAM.hotels()[0],
            price = Price.mocksINR[7],
            days = -64,
            duration = 15
        ),
        buildBooking(
            booker = booker,
            hotel = City.COCHIN.hotels()[0],
            price = Price.mocksINR[0],
            days = -49,
            duration = 9
        ),
        buildBooking(
            booker = booker,
            hotel = City.PUNE.hotels()[1],
            price = Price.mocksINR[1],
            days = -40,
            duration = 1
        ),
        buildBooking(
            booker = booker,
            hotel = City.DELHI.hotels()[2],
            price = Price.mocksINR[2],
            days = -39,
            duration = 1
        )
    )
}
