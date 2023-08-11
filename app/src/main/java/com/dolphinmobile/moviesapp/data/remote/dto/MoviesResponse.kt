package com.dolphinmobile.moviesapp.data.remote.dto

data class MoviesResponse(
      val page : Int,
      val totalPages : Int,
      val totalResults: Int,
      val results: List<MovieDTO>
)
