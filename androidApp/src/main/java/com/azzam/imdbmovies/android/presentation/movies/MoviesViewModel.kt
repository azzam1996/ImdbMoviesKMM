package com.azzam.imdbmovies.android.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azzam.imdbmovies.android.domain.util.Event
import com.azzam.imdbmovies.domain.repository.MoviesRepository
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.domain.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val moviesData = MutableLiveData<Event<List<Movie>?>>()
    val loading = MutableLiveData<Boolean>().apply { value = false }
    val error = MutableLiveData<Event<Result.Error<List<Movie>?>>>()


    fun getMovies() {
        viewModelScope.launch {
            loading.value = true
            when (val response = moviesRepository.getMovies()) {
                is Result.Success -> {
                    processResponse(response.value)
                }
                is Result.Error -> {
                    error.value = Event(response)
                }
            }
            loading.value = false
        }
    }


    fun insertMovie(movie: Movie) {
        Timber.v(movie.toString())
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.insertMovie(movie)
        }
    }

    fun searchForMovie(expression: String) {
        viewModelScope.launch {
            loading.value = true
            when (val response = moviesRepository.searchForMovie(expression)) {
                is Result.Success -> {
                    processResponse(response.value)
                }
                is Result.Error -> {
                    error.value = Event(response)
                }
            }
            loading.value = false
        }
    }


    private suspend fun processResponse(items: List<Movie>?) {
        val result = mutableListOf<Movie>()
        items?.let { result.addAll(it) }

        val favoriteMovies = moviesRepository.getFavoriteMovies()

        favoriteMovies.forEach {
            result.remove(it)
        }

        items?.mapIndexed { index, item ->
            var newItem = item
            val isFavorite = favoriteMovies.find { it.id == newItem?.id }
            isFavorite?.let {
                result.remove(item)
                newItem = newItem.copy(isFavorite = true)
                result.add(index, newItem)
            }
        }

        moviesData.value = Event(result)
    }
}