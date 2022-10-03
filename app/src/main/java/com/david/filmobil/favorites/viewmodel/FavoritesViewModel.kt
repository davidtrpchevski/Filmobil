package com.david.filmobil.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.FavoritesDao
import com.david.filmobil.database.entities.FavoriteMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesDao: FavoritesDao
) : ViewModel() {

    val favoritesMovieList = favoritesDao.getAllFavoriteMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun removeMovieFromFavorites(favoriteMovieModel: FavoriteMovieModel) {
        viewModelScope.launch {
            favoritesDao.deleteFavoriteMovie(favoriteMovieModel)
        }
    }
}