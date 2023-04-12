package com.azzam.imdbmovies.data.repository

import com.azzam.imdbmovies.database.*
import com.azzam.imdbmovies.domain.repository.*
import com.azzam.imdbmovies.domain.util.*
import com.azzam.imdbmovies.domain.model.*
import database.MovieEntity
import com.azzam.imdbmovies.data.remote.*
import com.azzam.imdbmovies.data.remote.dto.*
import com.azzam.imdbmovies.data.mappers.*

class MoviesRepositoryImpl(val api: Api, val db: MoviesDatabase) : MoviesRepository {

    override suspend fun getMovies(): Result<List<Movie>?> {
        return when (val response = safeApiCall { api.getMovies() }) {
            is Result.Success -> {
                Result.Success(response.value?.items?.toMoviesList())
            }
            is Result.Error -> {
                Result.Error(response.code, response.errorBody)
            }
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        db.movieQueries.insertMovie(
            movie.id,
            movie.rank,
            movie.rankUpDown,
            movie.title,
            movie.fullTitle,
            movie.year,
            movie.image,
            movie.crew,
            movie.imDbRating,
            movie.imDbRatingCount,
        )
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return db.movieQueries.getAllFavouriteMovies().executeAsList().map { it.toMovie() }
    }

    override suspend fun searchForMovie(expression: String): Result<List<Movie>?>{
        return when (val response =  safeApiCall { api.searchForMovie(expression) } ){
            is Result.Success -> {
                Result.Success(response.value?.results?.toMoviesList())
            }
            is Result.Error -> {
                Result.Error(response.code, response.errorBody)
            }
        }

    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T?> {
        return try {
            val response = apiCall.invoke()
            Result.Success(response)
        } catch (e: NetworkException) {
            Result.Error(
                code = e.code,
                errorBody = e.errorBody.toString()
            )
        } catch (e: Exception) {
            Result.Error(
                code = null,
                errorBody = e.message
            )
        }
    }
}