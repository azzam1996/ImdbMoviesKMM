package com.azzam.imdbmovies.di

import  com.azzam.imdbmovies.data.local.*
import  com.azzam.imdbmovies.data.repository.*
import  com.azzam.imdbmovies.domain.repository.*
import com.azzam.imdbmovies.database.MoviesDatabase
import com.azzam.imdbmovies.data.remote.*

class RepositoryModule {
    private val factory by lazy { DataBaseDriverFactory() }
    private val apiClient by lazy { ClientApi(KtorHttpClient.getKtorClient()) }
    val moviesRepository by lazy {
        MoviesRepositoryImpl(apiClient, MoviesDatabase(factory.createDriver()))
    }
}