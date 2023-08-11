package com.dolphinmobile.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDTO(
      @SerializedName("id")
      val id: Int,
      @SerializedName("title")
      val title: String? = null,
      @SerializedName("overview")
      val overview: String? = null,
      @SerializedName("poster_path")
      val posterPath: String? = null,
      @SerializedName("release_date")
      val releaseDate: String? = null,
      @SerializedName("vote_average")
      val voteAverage: Double? = null,
      @SerializedName("genres")
      val genres: List<GenresDTO>? = null
)
