package com.azzam.imdbmovies.data.remote

import com.azzam.imdbmovies.data.remote.dto.*
import io.ktor.client.HttpClient
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.response.*


class ClientApi(private val client: HttpClient) : Api {

    override suspend fun getMovies(): MoviesResponseDto? {
        return client.get {
            url("https://imdb-api.com/en/API/MostPopularMovies/k_tue0gur8")
        }
    }

    override suspend fun searchForMovie(expression: String): SearchResponseDto? {
        return client.get {
            url("https://imdb-api.com/en/API/Search/k_tue0gur8/" + expression)
        }
    }
}