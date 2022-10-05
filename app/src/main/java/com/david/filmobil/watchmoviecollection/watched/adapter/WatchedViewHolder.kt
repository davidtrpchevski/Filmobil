package com.david.filmobil.watchmoviecollection.watched.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.database.entities.WatchedMovieModel
import com.david.filmobil.databinding.ItemViewWatchedMovieBinding
import com.david.filmobil.utils.loadImageFromApi

class WatchedViewHolder(private val binding: ItemViewWatchedMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(watchedMovieModel: WatchedMovieModel, dataSavingEnabled: Boolean) {
        binding.moviePoster.loadImageFromApi(watchedMovieModel.posterPath, dataSavingEnabled)
        binding.movieTitle.text = watchedMovieModel.title
    }
}