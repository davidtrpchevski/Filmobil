package com.david.filmobil.paging

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.databinding.ItemViewPagingFooterBinding

class MovieLoadStateViewHolder(private val binding: ItemViewPagingFooterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        with(binding) {
            errorStateGroup.isVisible = loadState is LoadState.Error
            loadStateProgress.isVisible = loadState is LoadState.Loading
        }
    }
}
