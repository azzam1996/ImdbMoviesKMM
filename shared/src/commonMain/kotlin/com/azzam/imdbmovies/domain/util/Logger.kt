package com.azzam.imdbmovies.domain.util

expect object Logger {
    fun log(msg: String)
    fun log(label: String,msg: String)
}
