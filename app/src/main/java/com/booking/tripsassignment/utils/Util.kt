package com.booking.tripsassignment.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


inline fun ViewModel.startCoroutine(
    crossinline block: suspend CoroutineScope.() -> Unit,
    crossinline errorAction: (it: Throwable) -> Unit,
) {
    kotlin.runCatching {
        this.viewModelScope.launch {
            this.block()
        }
    }.onFailure{
        //care need test
        errorAction.invoke(it)
    }
}

sealed class LoadingStatus<T> {
    class InProgressLoadingStatus<T>(val showLoadingIcon: Boolean): LoadingStatus<T>()
    class ResultSuccessStatus<T>(val result: Result<T>): LoadingStatus<T>()
    class ResultEmptyDataStatus<T>(val result: Result<T>): LoadingStatus<T>()
    class ResultFailureStatus<T>(val result: Result<T>): LoadingStatus<T>()
}
