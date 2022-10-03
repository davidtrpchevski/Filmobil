package com.david.filmobil.searchresult.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.databinding.ItemViewSearchResultBinding
import com.david.filmobil.searchresult.model.SearchResultModel
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class SearchResultAdapter @Inject constructor() :
    ListAdapter<SearchResultModel, SearchResultViewHolder>(diffUtilCallback<SearchResultModel>()) {

    var onItemClick: ((SearchResultModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemViewSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = SearchResultViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick?.invoke(getItem(position))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}