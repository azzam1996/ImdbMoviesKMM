package com.azzam.imdbmovies.android.presentation.movies.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azzam.imdbmovies.android.R
import com.azzam.imdbmovies.android.databinding.ItemMovieBinding
import com.azzam.imdbmovies.domain.model.Movie
import com.azzam.imdbmovies.android.domain.util.loadImage


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var items = ArrayList<Movie>()

    interface OnMovieClickedListener {
        fun handleOnMovieClicked(movie: Movie)
    }

    var onMovieClickedListener: OnMovieClickedListener? = null

    interface OnAddToFavoriteClickedListener {
        fun handleAddToFavoriteClicked(movie: Movie, position: Int)
    }

    var onAddToFavoriteClickedListener: OnAddToFavoriteClickedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, null)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(list: List<Movie>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    public fun addMovieToFavouriteById(position: Int){
        val newItem = items[position].copy(isFavorite = true)
        items.removeAt(position)
        items.add(position,newItem)
        notifyItemChanged(position)
    }


    inner class MovieViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(interfaceImplementation: Movie, position: Int) = with(view) {
            val movie = interfaceImplementation as Movie
            val binding = ItemMovieBinding.bind(view)

            binding.ivPoster.loadImage(movie.image, binding.progressBar)
            binding.tvTitle.text = movie.title
            binding.tvRating.text = movie.imDbRating

            if (movie.isFavorite)
                binding.ivFavorite.setImageResource(android.R.drawable.star_big_on)
            else
                binding.ivFavorite.setImageResource(android.R.drawable.star_off)

            setOnClickListener { onMovieClickedListener?.handleOnMovieClicked(movie) }
            binding.ivFavorite.setOnClickListener {
                onAddToFavoriteClickedListener?.handleAddToFavoriteClicked(movie,position)
            }
        }
    }
}