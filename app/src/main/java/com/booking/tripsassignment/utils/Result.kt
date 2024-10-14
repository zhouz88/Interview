package com.booking.tripsassignment.utils

/**
 * A Result implementation class.
 * It's a sealed class which supports two types: Success And Error.
 */
sealed class Result<out R> {
    data class Success<out R>(val data: R) : Result<R>()
    data class Error(val exception: Exception) : Result<Nothing>()

    /**
     * Get the result data if it is Success else null.
     */
    fun data(): R? {
        return when (this) {
            is Success -> data
            else -> null
        }
    }

    fun <T> mapOnSuccess(mapper: (R) -> T): Result<T> {
        return when (this) {
            is Success -> Result.Success(mapper(data))
            is Error -> Result.Error(exception)
        }
    }
}