package com.booking.tripsassignment.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


inline fun ViewModel.startCoroutine(
    crossinline block: suspend CoroutineScope.() -> Unit,
    crossinline errorAction: (it: Throwable) -> Unit,
) {
    val coroutineHandler = CoroutineExceptionHandler {_, throwable ->
        this.viewModelScope.launch(Dispatchers.Main) {
            errorAction.invoke(throwable)
        }
    }
    this.viewModelScope.launch(coroutineHandler) {
        this.block()
    }
}

sealed class LoadingStatus<T> {
    class InProgressLoadingStatus<T>(val showLoadingIcon: Boolean): LoadingStatus<T>()
    class ResultSuccessStatus<T>(val result: Result<T>): LoadingStatus<T>()
    class ResultEmptyDataStatus<T>(val result: Result<T>): LoadingStatus<T>()
    class ResultFailureStatus<T>(val result: Result<T>): LoadingStatus<T>()
}


fun formatTripDate(date1: LocalDate, date2: LocalDate) : String{


    val formatter1: DateTimeFormatter = DateTimeFormat.forPattern("dd")
    val formatter2: DateTimeFormatter = DateTimeFormat.forPattern("dd MMM")
    val formatter3: DateTimeFormatter = DateTimeFormat.forPattern("dd MMM yyyy")

    if (date1.year == date2.year && date1.monthOfYear == date2.monthOfYear) {
        val result1: String = date1.toString(formatter1)
        val result2 = date2.toString(formatter3)
        return "$result1-$result2"
    } else if (date1.year == date2.year) {
        val result1: String = date1.toString(formatter2)
        val result2 = date2.toString(formatter3)
        return "$result1-$result2"
    } else {
        val result1: String = date1.toString(formatter3)
        val result2 = date2.toString(formatter3)
        return "$result1-$result2"
    }
}

fun dp2px(context: Context, dipValue: Float): Int {
    val scale = context.applicationContext.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}
