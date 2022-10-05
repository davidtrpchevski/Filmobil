package com.david.filmobil.watchmoviecollection.watchlist.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.database.entities.WatchlistMoviesModel
import com.david.filmobil.databinding.ItemViewWatchlistMovieBinding
import com.david.filmobil.utils.loadImageFromApi

class WatchlistViewHolder(private val binding: ItemViewWatchlistMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(watchlistMoviesModel: WatchlistMoviesModel, dataSavingEnabled: Boolean) {
        binding.moviePoster.loadImageFromApi(watchlistMoviesModel.posterPath, dataSavingEnabled)
        binding.movieTitle.text = watchlistMoviesModel.title
    }
}