package com.booking.tripsassignment.modules

import com.booking.tripsassignment.repository.BookingRepository
import com.booking.tripsassignment.repository.MockNetworkBookingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.lang.annotation.Documented
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserActivityModule {

    @Provides
    @Singleton
    @MockBooking
    fun provideBookingService(): BookingRepository {
        return MockNetworkBookingRepository()
    }
}


@Qualifier
@Documented
annotation class MockBooking

@Qualifier
annotation class ProductBookingService

