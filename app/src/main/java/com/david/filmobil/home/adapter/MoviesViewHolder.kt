package com.david.filmobil.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.david.filmobil.R
import com.david.filmobil.databinding.ItemViewMovieBinding
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.utils.loadImageFromUrl

class MoviesViewHolder(private val binding: ItemViewMovieBinding) : ViewHolder(binding.root) {
    fun bind(movieModel: MovieModel) {
        binding.movieTitle.text = movieModel.title
        binding.movieDescription.text = movieModel.overview
        binding.movieCover.load(loadImageFromUrl(movieModel.posterPath)) {
            placeholder(R.drawable.ic_baseline_image)
            crossfade(true)
        }
    }
}