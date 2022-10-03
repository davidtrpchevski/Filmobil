package com.david.filmobil.favorites.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.databinding.ItemViewFavoriteMovieBinding
import com.david.filmobil.utils.loadImageFromUrl

class FavoritesMovieViewHolder(private val binding: ItemViewFavoriteMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(favoriteMovieModel: FavoriteMovieModel) {
        binding.moviePoster.load(loadImageFromUrl(favoriteMovieModel.posterPath))
        binding.movieTitle.text = favoriteMovieModel.title
    }
}