package com.david.filmobil.searchresult.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.di.dispatchers.IoDispatcher
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
    private val savedStateHandle: SavedStateHandle
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
            _searchData.value = remoteService.getMoviesByTitle(searchQuery).unpackResult()
        }
    }
}