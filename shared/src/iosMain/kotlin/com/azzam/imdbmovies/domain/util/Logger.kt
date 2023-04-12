package com.azzam.imdbmovies.domain.util

actual object Logger {
    actual fun log(msg: String) {
        print(msg + "\n")
    }

    actual fun log(label: String, msg: String) {
        print(label + " : " + msg + "\n")
    }
}