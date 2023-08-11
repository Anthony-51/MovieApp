package com.dolphinmobile.moviesapp.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.dolphinmobile.moviesapp.databinding.ItemMovieBinding
import com.dolphinmobile.moviesapp.domain.model.Movie

class MovieAdapter(
      private val movies: MutableList<Movie> = mutableListOf()
): Adapter<MovieAdapter.MovieVH>() {

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieVH {
            return MovieVH(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      }

      override fun onBindViewHolder(holder: MovieAdapter.MovieVH, position: Int) {
            holder.bind(movies[position])
      }

      @SuppressLint("NotifyDataSetChanged")
      fun add (movies: List<Movie>) {
            this.movies.clear()
            this.movies.addAll(movies)
            notifyDataSetChanged()
      }

      override fun getItemCount(): Int = movies.size
      inner class MovieVH(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(movie: Movie){
                  Glide.with(itemView).load("https://image.tmdb.org/t/p/w500${movie.posterPath}").into(binding.ivMovie)
                  binding.tvTitleMovie.text = movie.title
                  binding.tvDate.text = movie.releaseDate

            }
      }
}