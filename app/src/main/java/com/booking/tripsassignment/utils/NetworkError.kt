package com.booking.tripsassignment.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception

class NetworkError : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

    @RequiresApi(Build.VERSION_CODES.N)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    )
}