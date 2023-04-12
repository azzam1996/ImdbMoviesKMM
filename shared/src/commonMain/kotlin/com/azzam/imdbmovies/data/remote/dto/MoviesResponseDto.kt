package com.azzam.imdbmovies.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    @SerialName("items") val items: List<MovieDto>? = null,
    @SerialName("errorMessage") val errorMessage: String? = null,
)
