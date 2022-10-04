package com.david.filmobil.watchmoviecollection.watchlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.WatchlistMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(private val watchlistMoviesDao: WatchlistMoviesDao) :
    ViewModel() {

    val watchlistMoviesData = watchlistMoviesDao.getAllWatchlistMovies().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )
}