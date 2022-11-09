package com.david.filmobil.explore.adapter.inner

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.david.filmobil.databinding.ItemViewInnerHorizontalAdapterBinding
import com.david.filmobil.explore.adapter.inner.model.HorizontalInnerModel

class HorizontalInnerViewHolder(private val binding: ItemViewInnerHorizontalAdapterBinding) :
    ViewHolder(binding.root) {
    fun bind(horizontalInnerModel: HorizontalInnerModel) {
        binding.moviesList.adapter = horizontalInnerModel.adapter
        binding.header.text = horizontalInnerModel.title
    }
}