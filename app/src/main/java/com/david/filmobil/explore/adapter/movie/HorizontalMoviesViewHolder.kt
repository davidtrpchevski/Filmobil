package com.david.filmobil.explore.adapter.movie

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.databinding.ItemViewHorizontalMovieBinding
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.utils.loadImageFromApi

class HorizontalMoviesViewHolder(private val binding: ItemViewHorizontalMovieBinding) :
    ViewHolder(binding.root) {
    fun bind(watchedMovieModel: MovieModel, dataSavingEnabled: Boolean) {
        binding.moviePoster.loadImageFromApi(watchedMovieModel.posterPath, dataSavingEnabled)
        binding.movieTitle.text = watchedMovieModel.title
    }
}