package com.david.filmobil.watchmoviecollection.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.database.entities.WatchlistMoviesModel
import com.david.filmobil.databinding.ItemViewWatchlistMovieBinding
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class WatchlistAdapter @Inject constructor(private val dataSavingEnabled: DataSaverPrefUtil) :
    PagingDataAdapter<WatchlistMoviesModel, WatchlistViewHolder>(diffUtilCallback()) {

    var onItemClick: ((WatchlistMoviesModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val binding = ItemViewWatchlistMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = WatchlistViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { watchlistMoviesModel ->
                    onItemClick?.invoke(
                        watchlistMoviesModel
                    )
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, dataSavingEnabled.isDataSavingEnabled) }
    }
}