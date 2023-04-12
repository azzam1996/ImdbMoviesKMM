package com.azzam.imdbmovies.android.presentation.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azzam.imdbmovies.domain.repository.MoviesRepository
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.domain.util.*
import kotlinx.coroutines.launch

class FavouriteViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val favoriteMoviesData = MutableLiveData<List<Movie>?>()

    fun getFavoriteMovies() {
        viewModelScope.launch {
            favoriteMoviesData.value = moviesRepository.getFavoriteMovies()
        }
    }
}