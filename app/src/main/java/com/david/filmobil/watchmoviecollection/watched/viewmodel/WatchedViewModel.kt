package com.david.filmobil.watchmoviecollection.watched.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.WatchedMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(private val watchedMoviesDao: WatchedMoviesDao) :
    ViewModel() {

    val watchedMoviesData = watchedMoviesDao.getAllWatchedMovies().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )
}