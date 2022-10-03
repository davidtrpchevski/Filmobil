package com.david.filmobil.watchmoviecollection.watched.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.david.filmobil.database.entities.WatchedMovieModel
import com.david.filmobil.databinding.ItemViewWatchedMovieBinding
import com.david.filmobil.utils.loadImageFromUrl

class WatchedViewHolder(private val binding: ItemViewWatchedMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(watchedMovieModel: WatchedMovieModel) {
        binding.moviePoster.load(loadImageFromUrl(watchedMovieModel.posterPath))
        binding.movieTitle.text = watchedMovieModel.title
    }
}