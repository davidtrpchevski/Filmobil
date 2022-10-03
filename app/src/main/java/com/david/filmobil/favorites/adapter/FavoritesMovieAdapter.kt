package com.david.filmobil.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.databinding.ItemViewFavoriteMovieBinding
import com.david.filmobil.favorites.viewholder.FavoritesMovieViewHolder
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class FavoritesMovieAdapter @Inject constructor() :
    ListAdapter<FavoriteMovieModel, FavoritesMovieViewHolder>(
        diffUtilCallback<FavoriteMovieModel>()
    ) {

    var onFavoriteClick: ((FavoriteMovieModel) -> Unit)? = null
    var onFavoriteLongClick: ((FavoriteMovieModel, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMovieViewHolder {
        val binding =
            ItemViewFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = FavoritesMovieViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onFavoriteClick?.invoke(getItem(position))
            }
        }

        holder.itemView.setOnLongClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onFavoriteLongClick?.invoke(getItem(position), holder.itemView)
            }
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: FavoritesMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}