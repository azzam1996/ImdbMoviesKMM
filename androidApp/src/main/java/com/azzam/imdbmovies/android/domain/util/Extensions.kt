package com.azzam.imdbmovies.android.domain.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import coil.load
import com.azzam.imdbmovies.android.R


fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
        target(
            onStart = { placeholder ->
                progressBar.visibility = View.VISIBLE
                this@loadImage.setImageDrawable(placeholder)
            },
            onSuccess = { result ->
                progressBar.visibility = View.GONE
                this@loadImage.setImageDrawable(result)
            },
            onError = { _ ->
                progressBar.visibility = View.GONE
            }
        )
    }
}