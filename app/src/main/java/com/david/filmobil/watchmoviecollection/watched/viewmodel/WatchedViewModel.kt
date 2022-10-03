package com.david.filmobil.watchmoviecollection.watched.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.WatchedMoviesDao
import com.david.filmobil.database.entities.WatchedMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(private val watchedMoviesDao: WatchedMoviesDao) :
    ViewModel() {

    private val _watchedMoviesData = MutableStateFlow<List<WatchedMovieModel>>(emptyList())
    val watchedMoviesData = _watchedMoviesData.asStateFlow()

    init {
        getAllWatchedMovies()
    }

    private fun getAllWatchedMovies() {
        viewModelScope.launch {
            _watchedMoviesData.value = watchedMoviesDao.getAllWatchedMovies()
        }
    }
}