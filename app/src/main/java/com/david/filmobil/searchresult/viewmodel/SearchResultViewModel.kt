package com.david.filmobil.searchresult.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazylegend.toaster.Toaster
import com.david.filmobil.di.qualifiers.IoDispatcher
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.network.result.unpackResult
import com.david.filmobil.searchresult.SearchResultFragmentArgs
import com.david.filmobil.searchresult.model.SearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val remoteService: RemoteService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle,
    private val toaster: Toaster,
) : ViewModel() {

    private val args get() = SearchResultFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val searchQuery = args.searchQuery

    private val _searchData = MutableStateFlow<ApiResult<SearchModel>>(ApiResult.Initial)
    val searchData = _searchData.asStateFlow()

    init {
        searchForMovieByTitle()
    }

    private fun searchForMovieByTitle() {
        viewModelScope.launch(ioDispatcher) {
            _searchData.value = unpackResult {
                remoteService.getMoviesByTitle(searchQuery)
            }
        }
    }


    fun <T> showErrorToast(errorResult: ApiResult<T>) {
        when (errorResult) {
            is ApiResult.ApiError -> toaster.shortToast("Body: ${errorResult.responseBody?.string()} & Code: ${errorResult.errorCode}")
            is ApiResult.Error -> toaster.shortToast(errorResult.throwable.toString())
            else -> {}
        }
    }

}