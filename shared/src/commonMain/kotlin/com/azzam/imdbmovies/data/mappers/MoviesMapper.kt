package com.azzam.imdbmovies.data.mappers

import database.MovieEntity
import com.azzam.imdbmovies.data.remote.dto.MovieDto
import com.azzam.imdbmovies.domain.model.Movie


fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        rank = rank,
        rankUpDown = rankUpDown,
        title = title,
        fullTitle = fullTitle,
        year = year,
        image = image,
        crew = crew,
        imDbRating = imDbRating,
        imDbRatingCount = imDbRatingCount,
        isFavorite = true
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        rank = rank,
        rankUpDown = rankUpDown,
        title = title,
        fullTitle = fullTitle,
        year = year,
        image = image,
        crew = crew,
        imDbRating = imDbRating,
        imDbRatingCount = imDbRatingCount,
        isFavorite = false
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        rank = rank,
        rankUpDown = rankUpDown,
        title = title,
        fullTitle = fullTitle,
        year = year,
        image = image,
        crew = crew,
        imDbRating = imDbRating,
        imDbRatingCount = imDbRatingCount,
    )
}


fun List<MovieDto>.toMoviesList(): List<Movie> {
    return this.map {
        it.toMovie()
    }
}