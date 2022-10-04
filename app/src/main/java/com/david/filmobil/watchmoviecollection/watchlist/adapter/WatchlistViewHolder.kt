package com.david.filmobil.watchmoviecollection.watchlist.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.david.filmobil.database.entities.WatchlistMoviesModel
import com.david.filmobil.databinding.ItemViewWatchlistMovieBinding
import com.david.filmobil.utils.loadImageFromUrl

class WatchlistViewHolder(private val binding: ItemViewWatchlistMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(watchlistMoviesModel: WatchlistMoviesModel) {
        binding.moviePoster.load(loadImageFromUrl(watchlistMoviesModel.posterPath))
        binding.movieTitle.text = watchlistMoviesModel.title
    }
}