package com.azzam.imdbmovies.domain.repository

import com.azzam.imdbmovies.domain.util.Result
import com.azzam.imdbmovies.domain.model.*
import com.azzam.imdbmovies.data.remote.dto.*

interface MoviesRepository {
    suspend fun getMovies(): Result<List<Movie>?>
    suspend fun searchForMovie(expression: String): Result<List<Movie>?>

    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun insertMovie(movie: Movie)
}