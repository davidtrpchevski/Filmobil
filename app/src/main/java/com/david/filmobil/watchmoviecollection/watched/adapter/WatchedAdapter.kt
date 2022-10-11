package com.david.filmobil.watchmoviecollection.watched.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.database.entities.WatchedMovieModel
import com.david.filmobil.databinding.ItemViewWatchedMovieBinding
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class WatchedAdapter @Inject constructor(private val dataSavingEnabled: DataSaverPrefUtil) :
    PagingDataAdapter<WatchedMovieModel, WatchedViewHolder>(diffUtilCallback()) {

    var onItemClick: ((WatchedMovieModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchedViewHolder {
        val binding =
            ItemViewWatchedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = WatchedViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { watchedMovieModel ->
                    onItemClick?.invoke(
                        watchedMovieModel
                    )
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: WatchedViewHolder, position: Int) {
        getItem(position)?.let { watchedMovieModel ->
            holder.bind(
                watchedMovieModel,
                dataSavingEnabled.isDataSavingEnabled
            )
        }
    }
}