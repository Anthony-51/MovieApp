package com.dolphinmobile.moviesapp.domain.model

import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
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

fun MovieDTO.toDomain(isFavorite: Boolean = false) = Movie(
      id = id,
      title = title?: "",
      overview = overview?: "",
      posterPath = posterPath?: "",
      releaseDate = releaseDate?: "",
      voteAverage = voteAverage?: 0.0,
      isFavorite = isFavorite,
      genres = genres?.map { it.name }?: emptyList()
)

fun Movie.toEntity() = MovieEntity(
      id = id,
      title = title,
      posterPath = posterPath,
      releaseDate = releaseDate
)

fun MovieEntity.toDomain() = Movie(
      id = id,
      title = title,
      posterPath = posterPath,
      releaseDate = releaseDate,
      voteAverage = 0.0,
      isFavorite = true,
      genres = emptyList(),
      overview = ""
)
