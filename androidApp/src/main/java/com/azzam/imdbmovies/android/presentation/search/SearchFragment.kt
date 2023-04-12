package com.azzam.imdbmovies.android.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.azzam.imdbmovies.android.R
import com.azzam.imdbmovies.android.databinding.FragmentSearchBinding
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.android.presentation.dialogs.DisplayMovieDialog
import com.azzam.imdbmovies.android.presentation.movies.adapter.MoviesAdapter
import com.azzam.imdbmovies.android.presentation.movies.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search), MoviesAdapter.OnMovieClickedListener,
    MoviesAdapter.OnAddToFavoriteClickedListener {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    private lateinit var _moviesBinding: FragmentSearchBinding

    private val moviesAdapter = MoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _moviesBinding = FragmentSearchBinding.bind(view)
        initAdapter()
        observeViewModel()

        _moviesBinding.ivSearch.setOnClickListener {
            moviesViewModel.searchForMovie(_moviesBinding.etSearch.text.toString())
        }

    }

    private fun initAdapter() {
        val verticalLayoutManager = GridLayoutManager(
            activity, 1,
            GridLayoutManager.VERTICAL, false
        )

        moviesAdapter.onMovieClickedListener = this
        moviesAdapter.onAddToFavoriteClickedListener = this

        _moviesBinding.rvMovies.layoutManager = verticalLayoutManager
        _moviesBinding.rvMovies.adapter = moviesAdapter
    }

    private fun observeViewModel() {
        moviesViewModel.loading.observe(viewLifecycleOwner) {
            when (it) {
                true -> _moviesBinding.loading.visibility = View.VISIBLE
                else -> _moviesBinding.loading.visibility = View.GONE
            }
        }

        moviesViewModel.error.observe(viewLifecycleOwner){
            if(it.isNotConsumed()){
                val error = it.consume()
                Toast.makeText(requireContext(),error?.errorBody.toString(),Toast.LENGTH_LONG).show()
            }
        }

        moviesViewModel.moviesData.observe(viewLifecycleOwner) {
            initAdapter()
            if(it.isNotConsumed()){
                val response = it.consume()
                response?.let { movies ->
                    moviesAdapter.addItems(movies)
                }
            }
        }
    }

    override fun handleOnMovieClicked(movie: Movie) {
        DisplayMovieDialog(movie).show(childFragmentManager, "DisplayMovieDialog")
    }

    override fun handleAddToFavoriteClicked(movie: Movie, position: Int) {
        moviesAdapter.addMovieToFavouriteById(position)
        moviesViewModel.insertMovie(movie)
    }
}