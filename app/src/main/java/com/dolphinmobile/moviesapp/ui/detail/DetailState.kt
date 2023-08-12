package com.dolphinmobile.moviesapp.ui.detail

import com.dolphinmobile.moviesapp.domain.model.Movie

data class DetailState(
      val movie: Movie? = null,
      val isLoading: Boolean = false,
      val error: String = ""
)
