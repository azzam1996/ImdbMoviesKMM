package com.azzam.imdbmovies.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName("results") val results: List<MovieDto>? = null,
    @SerialName("expression") val expression: String? = null,
)
