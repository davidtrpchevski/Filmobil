package com.david.filmobil.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.databinding.ItemViewMovieBinding
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class MoviesAdapter @Inject constructor(private val dataSavingEnabled: DataSaverPrefUtil) :
    PagingDataAdapter<MovieModel, MoviesViewHolder>(diffUtilCallback()) {

    var onItemClick: ((MovieModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ItemViewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = MoviesViewHolder(binding)

        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { movieModel -> onItemClick?.invoke(movieModel) }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { movieModel ->
            holder.bind(
                movieModel,
                dataSavingEnabled.isDataSavingEnabled
            )
        }
    }

}