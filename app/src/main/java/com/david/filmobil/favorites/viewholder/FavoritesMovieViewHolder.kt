package com.david.filmobil.favorites.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.databinding.ItemViewFavoriteMovieBinding
import com.david.filmobil.utils.loadImageFromApi

class FavoritesMovieViewHolder(private val binding: ItemViewFavoriteMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(favoriteMovieModel: FavoriteMovieModel, dataSavingEnabled: Boolean) {
        binding.moviePoster.loadImageFromApi(favoriteMovieModel.posterPath, dataSavingEnabled)
        binding.movieTitle.text = favoriteMovieModel.title
    }
}