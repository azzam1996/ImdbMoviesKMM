package com.azzam.imdbmovies.data.remote

import com.azzam.imdbmovies.data.remote.dto.*

interface Api {
    suspend fun getMovies(): MoviesResponseDto?
    suspend fun searchForMovie(expression: String): SearchResponseDto?
}