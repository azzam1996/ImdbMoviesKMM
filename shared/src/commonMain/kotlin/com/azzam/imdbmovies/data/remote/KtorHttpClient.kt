package com.azzam.imdbmovies.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import com.azzam.imdbmovies.data.remote.NetworkException
import com.azzam.imdbmovies.domain.util.*

expect object KtorHttpClient {
    fun getKtorClient(): HttpClient
}