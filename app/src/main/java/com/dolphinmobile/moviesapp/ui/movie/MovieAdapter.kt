package com.dolphinmobile.moviesapp.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.dolphinmobile.moviesapp.databinding.ItemFavoriteBinding
import com.dolphinmobile.moviesapp.databinding.ItemMovieBinding
import com.dolphinmobile.moviesapp.domain.model.Movie
import com.dolphinmobile.moviesapp.util.Constants
import com.dolphinmobile.moviesapp.util.DateFormat.formatDate

class MovieAdapter(
      private val viewType: Int,
      private val movies: MutableList<Movie> = mutableListOf(),
      private val itemSelected: (Int) -> Unit
): Adapter<RecyclerView.ViewHolder>() {
      override fun getItemViewType(position: Int): Int {
            return viewType
      }

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == 2) {
                  FavoriteVH(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }else MovieVH(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      }

      override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when(holder){
                  is MovieVH -> holder.bind(movies[position])
                  is FavoriteVH -> holder.bind(movies[position])
            }
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
                  Glide.with(itemView).load("${Constants.IMAGE_URL}${movie.posterPath}").into(binding.ivMovie)
                  binding.tvTitleMovie.text = movie.title
                  binding.tvDate.text = movie.releaseDate.formatDate("EEEE dd 'de' MMMM 'del' yyyy")
                  binding.rbAverage.rating = movie.voteAverage.toFloat()
                  binding.root.setOnClickListener {
                        itemSelected.invoke(movie.id)
                  }
            }
      }
      inner class FavoriteVH(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(movie: Movie){
                  Glide.with(itemView).load("${Constants.IMAGE_URL}${movie.posterPath}").into(binding.ivMovie)
                  binding.tvTitleMovie.text = movie.title
                  binding.tvDateRelease.text = movie.releaseDate.formatDate("dd MMMM yyyy")
                  binding.root.setOnClickListener {
                        itemSelected.invoke(movie.id)
                  }
            }
      }
}