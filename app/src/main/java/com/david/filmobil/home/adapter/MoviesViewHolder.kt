package com.david.filmobil.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.R
import com.david.filmobil.databinding.ItemViewMovieBinding
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.utils.loadImageFromApi

class MoviesViewHolder(private val binding: ItemViewMovieBinding) : ViewHolder(binding.root) {
    fun bind(movieModel: MovieModel, dataSavingEnabled: Boolean) {
        binding.movieTitle.text = movieModel.title
        binding.movieDescription.text = movieModel.overview
        binding.movieCover.loadImageFromApi(movieModel.posterPath, dataSavingEnabled) {
            placeholder(R.drawable.ic_baseline_image)
            crossfade(true)
        }
    }
}