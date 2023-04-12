package com.azzam.imdbmovies.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("id") val id: String,
    @SerialName("rank") val rank: String? = null,
    @SerialName("rankUpDown") val rankUpDown: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("fullTitle") val fullTitle: String? = null,
    @SerialName("year") val year: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("crew") val crew: String? = null,
    @SerialName("imDbRating") val imDbRating: String? = null,
    @SerialName("imDbRatingCount") val imDbRatingCount: String? = null,
    var isFavorite: Boolean = false,
)
