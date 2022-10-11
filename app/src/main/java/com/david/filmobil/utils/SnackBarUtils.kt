package com.david.filmobil.utils

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun showIndefiniteSnackBar(
    view: View,
    throwable: Throwable,
    @StringRes actionMessage: Int,
    doOnAction: () -> Unit
) {
    Snackbar.make(view, throwable.message.toString(), Snackbar.LENGTH_INDEFINITE)
        .setAction(actionMessage) { doOnAction() }.show()
}