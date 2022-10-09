package com.david.filmobil.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

val CombinedLoadStates.shouldShowProgressBar: Boolean
    get() = refresh is LoadState.Loading || append is LoadState.Loading

val CombinedLoadStates.hasErrorOccurred: Boolean
    get() = append is LoadState.Error || refresh is LoadState.Error || prepend is LoadState.Error

inline fun CombinedLoadStates.errorCheck(doOnError: (Throwable) -> Unit) {
    if (hasErrorOccurred) {
        source.forEach { _, loadState ->
            if (loadState is LoadState.Error) {
                doOnError(loadState.error)
            }
        }
    }
}