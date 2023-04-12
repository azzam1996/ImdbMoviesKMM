package com.azzam.imdbmovies.android.di

import com.azzam.imdbmovies.android.presentation.movies.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import  com.azzam.imdbmovies.data.local.DataBaseDriverFactory
import  com.azzam.imdbmovies.data.repository.MoviesRepositoryImpl
import  com.azzam.imdbmovies.domain.repository.MoviesRepository
import com.azzam.imdbmovies.database.MoviesDatabase
import com.azzam.imdbmovies.data.remote.ClientApi
import com.azzam.imdbmovies.data.remote.Api
import com.azzam.imdbmovies.data.remote.KtorHttpClient
import com.azzam.imdbmovies.android.presentation.favourite.FavouriteViewModel

import org.koin.dsl.module

val repositoryModule = module {
    single { MoviesDatabase(DataBaseDriverFactory(androidContext()).createDriver()) }
    single { MoviesRepositoryImpl(get(), get()) as MoviesRepository }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
}

val networkModule = module {
    single { KtorHttpClient.getKtorClient() }
    single { ClientApi(get()) as Api }
}