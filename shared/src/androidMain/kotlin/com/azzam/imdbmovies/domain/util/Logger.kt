package com.azzam.imdbmovies.domain.util

import android.util.Log

actual object Logger {
    actual fun log(msg: String) {
        Log.v("Message", msg)
    }

    actual fun log(label: String, msg: String) {
        Log.v(label, msg)
    }
}