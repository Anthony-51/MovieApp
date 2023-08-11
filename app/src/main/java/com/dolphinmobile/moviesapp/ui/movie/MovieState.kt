package com.dolphinmobile.moviesapp.ui.movie

import com.dolphinmobile.moviesapp.domain.model.Movie

data class MovieState(
      val movies: List<Movie> = emptyList(),
      val isLoading: Boolean = false,
      val error: String = ""
)
