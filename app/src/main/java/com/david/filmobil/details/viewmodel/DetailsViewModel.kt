package com.david.filmobil.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazylegend.toaster.Toaster
import com.david.filmobil.database.dao.FavoritesDao
import com.david.filmobil.database.dao.WatchedMoviesDao
import com.david.filmobil.database.dao.WatchlistMoviesDao
import com.david.filmobil.details.DetailsFragmentArgs
import com.david.filmobil.details.model.MovieDetailsModel
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.network.result.getResultAsSuccess
import com.david.filmobil.network.result.unpackResult
import com.david.filmobil.utils.mapToMovieDbModel
import com.david.filmobil.utils.mapToWatchedMovieModel
import com.david.filmobil.utils.mapToWatchlistModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val remoteService: RemoteService,
    savedStateHandle: SavedStateHandle,
    private val toaster: Toaster,
    private val favoritesDao: FavoritesDao,
    private val watchedMoviesDao: WatchedMoviesDao,
    private val watchlistMoviesDao: WatchlistMoviesDao
) : ViewModel() {

    private val args = DetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val movieId = args.movieId

    private val _isAddToFabPressed = MutableStateFlow(false)
    val isAddToFabPressed = _isAddToFabPressed.asStateFlow()

    private val _movieDetails = MutableStateFlow<ApiResult<MovieDetailsModel>>(ApiResult.Initial)
    val movieDetails = _movieDetails.asStateFlow()

    private val _isMovieInFavorites = MutableStateFlow(false)
    val isMovieInFavorites = _isMovieInFavorites.asStateFlow()

    init {
        viewModelScope.launch {
            getMovieDetails()
            checkIfMovieIsInFavorites()
        }
    }

    private suspend fun checkIfMovieIsInFavorites() {
        _isMovieInFavorites.value = favoritesDao.isMovieInFavorites(movieId)
    }

    private suspend fun getMovieDetails() {
        _movieDetails.value = ApiResult.Loading
        _movieDetails.value = unpackResult {
            remoteService.getMovie(movieId)
        }
    }

    fun <T> showErrorToast(errorResult: ApiResult<T>) {
        when (errorResult) {
            is ApiResult.ApiError -> toaster.shortToast("Body: ${errorResult.responseBody?.string()} & Code: ${errorResult.errorCode}")
            is ApiResult.Error -> toaster.shortToast(errorResult.throwable.toString())
            else -> {}
        }
    }

    fun handleMovieIntoDatabase() {
        val fetchedMovieDetails =
            _movieDetails.value.getResultAsSuccess()?.mapToMovieDbModel() ?: return
        viewModelScope.launch {
            if (_isMovieInFavorites.value) {
                favoritesDao.deleteFavoriteMovie(fetchedMovieDetails)
            } else {
                favoritesDao.insertFavoriteMovie(fetchedMovieDetails)
            }
            checkIfMovieIsInFavorites()
        }
    }

    fun handleMovieToWatchedList() {
        val watchedMovie =
            _movieDetails.value.getResultAsSuccess()?.mapToWatchedMovieModel() ?: return
        viewModelScope.launch {
            if (!watchedMoviesDao.isMovieInWatched(watchedMovie.id)) {
                watchedMoviesDao.insertWatchedMovie(watchedMovie)
            } else {
                watchedMoviesDao.deleteWatchedMovie(watchedMovie)
            }
        }
    }

    fun handleMovieToWatchlist() {
        val watchlistMovie =
            _movieDetails.value.getResultAsSuccess()?.mapToWatchlistModel() ?: return
        viewModelScope.launch {
            if (!watchlistMoviesDao.isMovieInWatchlist(watchlistMovie.id)) {
                watchlistMoviesDao.insertMovieToWatchlist(watchlistMovie)
            } else {
                watchlistMoviesDao.deleteMovieFromWatchlist(watchlistMovie)
            }
        }
    }

    fun setAddToFabPressStatus() {
        _isAddToFabPressed.value = !_isAddToFabPressed.value
    }
}