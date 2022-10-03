package com.david.filmobil.searchresult.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.david.filmobil.R
import com.david.filmobil.databinding.ItemViewSearchResultBinding
import com.david.filmobil.searchresult.model.SearchResultModel
import com.david.filmobil.utils.loadImageFromUrl

class SearchResultViewHolder(private val binding: ItemViewSearchResultBinding) :
    ViewHolder(binding.root) {
    fun bind(searchResultModel: SearchResultModel) {
        binding.movieTitle.text = searchResultModel.title
        binding.movieDescription.text = searchResultModel.overview
        binding.movieCover.load(loadImageFromUrl(searchResultModel.posterPath)) {
            placeholder(R.drawable.ic_baseline_image)
            crossfade(true)
        }
    }
}