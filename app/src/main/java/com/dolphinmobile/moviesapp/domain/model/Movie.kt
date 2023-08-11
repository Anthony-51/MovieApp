package com.dolphinmobile.moviesapp.domain.model

import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO

data class Movie(
      val id: Int,
      val title: String,
      val overview: String,
      val posterPath: String,
      val releaseDate: String,
      val voteAverage: Double,
      val isFavorite: Boolean,
      val genres: List<String>
)

fun MovieDTO.toDomain() = Movie(
      id = id,
      title = title?: "",
      overview = overview?: "",
      posterPath = posterPath?: "",
      releaseDate = releaseDate?: "",
      voteAverage = voteAverage?: 0.0,
      isFavorite = false,
      genres = genres?.map { it.name }?: emptyList()
)
