package com.david.filmobil.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.david.filmobil.databinding.ItemViewPagingFooterBinding
import javax.inject.Inject

class MovieLoadStateAdapter @Inject constructor() : LoadStateAdapter<MovieLoadStateViewHolder>() {

    var onRetry: (() -> Unit)? = null
    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateViewHolder {
        val binding = ItemViewPagingFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = MovieLoadStateViewHolder(binding)
        binding.loadStateRetry.setOnClickListener {
            onRetry?.invoke()
        }
        return holder
    }
}