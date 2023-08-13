package com.dolphinmobile.moviesapp.ui.favorites

import com.dolphinmobile.moviesapp.domain.model.Movie

data class FavoriteState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Movie> = emptyList()
)
