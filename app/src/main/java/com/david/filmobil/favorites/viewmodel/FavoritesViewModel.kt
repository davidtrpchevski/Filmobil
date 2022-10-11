package com.david.filmobil.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.david.filmobil.constants.LOCAL_DATABASE_PAGING_SIZE
import com.david.filmobil.database.dao.FavoritesDao
import com.david.filmobil.database.entities.FavoriteMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesDao: FavoritesDao
) : ViewModel() {

    val favoritesMovieList = Pager(PagingConfig(LOCAL_DATABASE_PAGING_SIZE)) {
        favoritesDao.getAllFavoriteMovies()
    }.flow.cachedIn(viewModelScope)

    fun removeMovieFromFavorites(favoriteMovieModel: FavoriteMovieModel) {
        viewModelScope.launch {
            favoritesDao.deleteFavoriteMovie(favoriteMovieModel)
        }
    }
}