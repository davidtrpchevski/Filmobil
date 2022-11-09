package com.david.filmobil.explore.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.databinding.ItemViewHorizontalMovieBinding
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class HorizontalMoviesAdapter @Inject constructor(private val dataSavingEnabled: DataSaverPrefUtil) :
    ListAdapter<MovieModel, HorizontalMoviesViewHolder>(diffUtilCallback<MovieModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMoviesViewHolder {
        val binding = ItemViewHorizontalMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HorizontalMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalMoviesViewHolder, position: Int) {
        holder.bind(getItem(position), dataSavingEnabled.isDataSavingEnabled)
    }
}