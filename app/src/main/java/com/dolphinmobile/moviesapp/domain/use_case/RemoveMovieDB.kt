package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.data.repository.MovieRepository
import com.dolphinmobile.moviesapp.domain.model.Movie
import com.dolphinmobile.moviesapp.domain.model.toEntity
import javax.inject.Inject

class RemoveMovieDB @Inject constructor(
  private val repository: MovieRepository
){
  suspend operator fun invoke(movie: Movie) = repository.removeMovie(movie.toEntity())
}