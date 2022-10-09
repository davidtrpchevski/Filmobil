package com.david.filmobil.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadType

val CombinedLoadStates.shouldShowProgressBarOnInitialLoad: Boolean
    get() = refresh is LoadState.Loading

val CombinedLoadStates.hasErrorOccurred: Boolean
    get() = append is LoadState.Error || refresh is LoadState.Error || prepend is LoadState.Error

inline fun CombinedLoadStates.errorCheck(
    doOnInitialError: (Throwable) -> Unit = { _ -> },
    doOnPaginationError: (Throwable) -> Unit = { _ -> }
) {
    if (hasErrorOccurred) {
        source.forEach { loadType, loadState ->
            if (loadState is LoadState.Error) {
                if (loadType == LoadType.REFRESH) {
                    doOnInitialError(loadState.error)
                } else {
                    doOnPaginationError(loadState.error)
                }
            }
        }
    }
}