package com.david.filmobil.search.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.database.entities.SearchHistoryModel
import com.david.filmobil.databinding.ItemViewSearchHistoryBinding

class SearchHistoryViewHolder(
    private val binding: ItemViewSearchHistoryBinding,
    private val onRemoveClick: ((SearchHistoryModel) -> Unit)?
) : ViewHolder(binding.root) {
    fun bind(searchHistoryModel: SearchHistoryModel) {
        binding.searchQueryText.text = searchHistoryModel.searchHistoryQuery
        binding.removeSearchQuery.setOnClickListener {
            onRemoveClick?.invoke(searchHistoryModel)
        }
    }
}