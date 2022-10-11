package com.david.filmobil.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.databinding.ItemViewFavoriteMovieBinding
import com.david.filmobil.favorites.viewholder.FavoritesMovieViewHolder
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class FavoritesMovieAdapter @Inject constructor(private val dataSaverPrefUtil: DataSaverPrefUtil) :
    PagingDataAdapter<FavoriteMovieModel, FavoritesMovieViewHolder>(diffUtilCallback()) {

    var onFavoriteClick: ((FavoriteMovieModel) -> Unit)? = null
    var onFavoriteLongClick: ((FavoriteMovieModel, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMovieViewHolder {
        val binding =
            ItemViewFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = FavoritesMovieViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { favoriteMovieModel ->
                    onFavoriteClick?.invoke(
                        favoriteMovieModel
                    )
                }
            }
        }

        holder.itemView.setOnLongClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { favoriteMovieModel ->
                    onFavoriteLongClick?.invoke(
                        favoriteMovieModel,
                        holder.itemView
                    )
                }
            }
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: FavoritesMovieViewHolder, position: Int) {
        getItem(position)?.let { favoriteMovieModel ->
            holder.bind(
                favoriteMovieModel,
                dataSaverPrefUtil.isDataSavingEnabled
            )
        }
    }
}