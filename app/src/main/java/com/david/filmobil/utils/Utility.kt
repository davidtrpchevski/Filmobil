package com.david.filmobil.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.request.Disposable
import coil.request.ImageRequest
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

fun ImageView.loadImageFromApi(
    imagePath: String?,
    isDataSaverEnabled: Boolean,
    builder: ImageRequest.Builder.() -> Unit = {}
): Disposable {
    val imageSize = if (isDataSaverEnabled) "w500" else "original"
    return load("${BuildConfig.API_IMAGE_URL}/$imageSize/${imagePath}", builder = builder)
}

fun getImdbUrl(imdbId: String?) = "$IMDB_SHARE_ID_URL/${imdbId}"