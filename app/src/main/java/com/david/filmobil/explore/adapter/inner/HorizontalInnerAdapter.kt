package com.david.filmobil.explore.adapter.inner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.filmobil.databinding.ItemViewInnerHorizontalAdapterBinding
import com.david.filmobil.explore.adapter.inner.model.HorizontalInnerModel
import com.david.filmobil.utils.diffUtilCallback
import javax.inject.Inject

class HorizontalInnerAdapter @Inject constructor() :
    ListAdapter<HorizontalInnerModel, HorizontalInnerViewHolder>(diffUtilCallback()) {

    var onHeaderClick: ((HorizontalInnerModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalInnerViewHolder {
        val binding = ItemViewInnerHorizontalAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = HorizontalInnerViewHolder(binding)
        binding.header.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onHeaderClick?.invoke(getItem(position))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: HorizontalInnerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}