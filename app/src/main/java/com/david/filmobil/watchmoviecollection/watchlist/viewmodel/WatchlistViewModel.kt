package com.david.filmobil.watchmoviecollection.watchlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.WatchlistMoviesDao
import com.david.filmobil.database.entities.WatchlistMoviesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(private val watchlistMoviesDao: WatchlistMoviesDao) :
    ViewModel() {

    private val _watchlistMoviesData = MutableStateFlow<List<WatchlistMoviesModel>>(emptyList())
    val watchlistMoviesData = _watchlistMoviesData.asStateFlow()

    init {
        getAllWatchlistMovies()
    }

    fun getAllWatchlistMovies() {
        viewModelScope.launch {
            _watchlistMoviesData.value = watchlistMoviesDao.getAllWatchlistMovies()
        }
    }

}