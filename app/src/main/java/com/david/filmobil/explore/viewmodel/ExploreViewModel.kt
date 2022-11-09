package com.david.filmobil.explore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.home.model.ResultModel
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.network.result.unpackResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val remoteService: RemoteService) : ViewModel() {

    private val _popularMoviesData = MutableStateFlow<ApiResult<ResultModel>>(ApiResult.Initial)
    val popularMoviesData = _popularMoviesData.asStateFlow()

    private val _topRatedMoviesData = MutableStateFlow<ApiResult<ResultModel>>(ApiResult.Initial)
    val topRatedMoviesData = _topRatedMoviesData.asStateFlow()

    private val _trendingMoviesData =
        MutableStateFlow<ApiResult<ResultModel>>(ApiResult.Initial)
    val trendingMoviesData = _trendingMoviesData.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies()
            getTopRatedMovies()
            getTrendingMovies()
        }
    }

    suspend fun getPopularMovies() {
        _popularMoviesData.value = unpackResult { remoteService.getPopularMovies() }
    }

    suspend fun getTopRatedMovies() {
        _topRatedMoviesData.value = unpackResult { remoteService.getTopRatedMovies() }
    }

    suspend fun getTrendingMovies() {
        _trendingMoviesData.value = unpackResult { remoteService.getTrendingMovies() }
    }


}