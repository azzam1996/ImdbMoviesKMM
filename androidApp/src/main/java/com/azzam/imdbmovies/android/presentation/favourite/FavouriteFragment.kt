package com.azzam.imdbmovies.android.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.azzam.imdbmovies.android.R
import com.azzam.imdbmovies.android.databinding.FragmentFavouriteBinding
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.android.presentation.dialogs.DisplayMovieDialog
import com.azzam.imdbmovies.android.presentation.movies.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment(R.layout.fragment_favourite),
    MoviesAdapter.OnMovieClickedListener {

    private val favouriteViewModel by viewModel<FavouriteViewModel>()
    private lateinit var _favouriteBinding: FragmentFavouriteBinding

    private val moviesAdapter = MoviesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favouriteBinding = FragmentFavouriteBinding.inflate(inflater)
        initAdapter()
        favouriteViewModel.getFavoriteMovies()
        observeViewModel()

        return _favouriteBinding.root
    }

    private fun initAdapter() {
        val verticalLayoutManager = GridLayoutManager(
            activity, 1,
            GridLayoutManager.VERTICAL, false
        )

        moviesAdapter.onMovieClickedListener = this

        _favouriteBinding.rvMovies.layoutManager = verticalLayoutManager
        _favouriteBinding.rvMovies.adapter = moviesAdapter
    }

    private fun observeViewModel() {

        favouriteViewModel.favoriteMoviesData.observe(viewLifecycleOwner) {
            it?.let { list ->
                moviesAdapter.addItems(list)
            }
        }
    }

    override fun handleOnMovieClicked(movie: Movie) {
        DisplayMovieDialog(movie).show(childFragmentManager, "DisplayMovieDialog")
    }
}