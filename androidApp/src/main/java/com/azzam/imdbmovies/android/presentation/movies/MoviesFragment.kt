package com.azzam.imdbmovies.android.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.azzam.imdbmovies.android.R
import com.azzam.imdbmovies.android.databinding.FragmentMoviesBinding
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.android.presentation.dialogs.DisplayMovieDialog
import com.azzam.imdbmovies.android.presentation.movies.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies), MoviesAdapter.OnMovieClickedListener,
    MoviesAdapter.OnAddToFavoriteClickedListener {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    private lateinit var _moviesBinding: FragmentMoviesBinding

    private val moviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _moviesBinding = FragmentMoviesBinding.inflate(inflater)
        return _moviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeViewModel()
        moviesViewModel.getMovies()
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

        moviesViewModel.loading.observe(viewLifecycleOwner){
            when(it){
                true -> {
                    _moviesBinding.loading.visibility = View.VISIBLE
                    _moviesBinding.rvMovies.visibility = View.GONE
                }
                else -> {
                    _moviesBinding.loading.visibility = View.GONE
                    _moviesBinding.rvMovies.visibility = View.VISIBLE
                }
            }
        }

        moviesViewModel.moviesData.observe(viewLifecycleOwner){
            if(it.isNotConsumed()){
                val response = it.consume()
                response?.let { movies ->
                    moviesAdapter.addItems(movies)
                }
            }
        }

        moviesViewModel.error.observe(viewLifecycleOwner){
            if(it.isNotConsumed()){
                val error = it.consume()
                Toast.makeText(requireContext(),error?.errorBody.toString(),Toast.LENGTH_LONG).show()
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