package com.david.filmobil.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.database.entities.SearchHistoryModel
import com.david.filmobil.databinding.ItemViewSearchHistoryBinding
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class SearchHistoryAdapter @Inject constructor() :
    ListAdapter<SearchHistoryModel, SearchHistoryViewHolder>(
        diffUtilCallback<SearchHistoryModel>()
    ) {

    var onRemoveClick: ((SearchHistoryModel) -> Unit)? = null
    var onItemClick: ((SearchHistoryModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding =
            ItemViewSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = SearchHistoryViewHolder(binding, onRemoveClick)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (RecyclerView.NO_POSITION != position) {
                onItemClick?.invoke(getItem(position))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}