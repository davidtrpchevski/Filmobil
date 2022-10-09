package com.david.filmobil.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.crazylegend.toaster.Toaster
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.paging.HomePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteService: RemoteService,
    private val toaster: Toaster
) : ViewModel() {

    private var page = 1
    val moviesList = Pager(
        config = PagingConfig(page),
        pagingSourceFactory = { HomePagingSource(remoteService) }).flow.cachedIn(viewModelScope)

    fun <T> showErrorToast(errorResult: ApiResult<T>) {
        when (errorResult) {
            is ApiResult.ApiError -> toaster.shortToast("Body: ${errorResult.responseBody?.string()} & Code: ${errorResult.errorCode}")
            is ApiResult.Error -> toaster.shortToast(errorResult.throwable.toString())
            else -> {}
        }
    }

}