package com.david.filmobil.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazylegend.toaster.Toaster
import com.david.filmobil.di.dispatchers.IoDispatcher
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.home.model.ResultModel
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.network.result.unpackResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteService: RemoteService,
    private val toaster: Toaster,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val savedMoviesList = mutableListOf<MovieModel>()

    private val _resultMoviesList = MutableStateFlow<ApiResult<ResultModel>>(ApiResult.Initial)
    val resultMoviesList = _resultMoviesList.asStateFlow()

    private val _moviesList = MutableSharedFlow<MutableList<MovieModel>>()
    val moviesList = _moviesList.asSharedFlow()

    private var page = 1

    init {
        fetchMovies()
    }

    private fun fetchMovies(nextPage: Int = page) {
        _resultMoviesList.value = ApiResult.Loading
        viewModelScope.launch(ioDispatcher) {
            try {
                _resultMoviesList.value = remoteService.getMoviesList(nextPage).unpackResult()
            } catch (throwable: Throwable) {
                showErrorToast(ApiResult.Error(throwable))
            }
        }
    }

    fun getNextPage() {
        fetchMovies(++page)
    }

    fun <T> showErrorToast(errorResult: ApiResult<T>) {
        when (errorResult) {
            is ApiResult.ApiError -> toaster.shortToast("Body: ${errorResult.responseBody?.string()} & Code: ${errorResult.errorCode}")
            is ApiResult.Error -> toaster.shortToast(errorResult.throwable.toString())
            else -> {}
        }
    }

    fun saveList(moviesList: List<MovieModel>) {
        viewModelScope.launch {
            savedMoviesList.addAll(moviesList)
            _moviesList.emit(savedMoviesList)
        }
    }

}