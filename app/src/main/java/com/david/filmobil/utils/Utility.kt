package com.david.filmobil.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.david.filmobil.BuildConfig
import com.david.filmobil.constants.IMDB_SHARE_ID_URL

fun <T : Any> diffUtilCallback(
    areItemsTheSameCallback: (oldItem: T, newItem: T) -> Boolean? = { _, _ -> null },
    areContentTheSameCallback: (oldItem: T, newItem: T) -> Boolean? = { _, _ -> null }
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSameCallback(oldItem, newItem) ?: (oldItem == newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentTheSameCallback(oldItem, newItem) ?: (oldItem == newItem)
}

fun loadImageFromUrl(posterPath: String?) = "${BuildConfig.API_IMAGE_URL}/${posterPath}"
fun getImdbUrl(imdbId: String?) = "$IMDB_SHARE_ID_URL/${imdbId}"