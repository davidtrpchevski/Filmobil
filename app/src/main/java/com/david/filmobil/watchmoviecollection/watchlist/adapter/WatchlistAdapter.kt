package com.david.filmobil.watchmoviecollection.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.database.entities.WatchlistMoviesModel
import com.david.filmobil.databinding.ItemViewWatchlistMovieBinding
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class WatchlistAdapter @Inject constructor(private val dataSavingEnabled: DataSaverPrefUtil) :
    ListAdapter<WatchlistMoviesModel, WatchlistViewHolder>(diffUtilCallback<WatchlistMoviesModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val binding = ItemViewWatchlistMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = WatchlistViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bind(getItem(position), dataSavingEnabled.isDataSavingEnabled)
    }
}