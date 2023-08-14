package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.domain.model.Movie
import com.dolphinmobile.moviesapp.domain.model.toEntity
import com.dolphinmobile.moviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveMovieDB @Inject constructor(
  private val repository: MovieRepository
){
  suspend operator fun invoke(movie: Movie) = repository.removeMovie(movie.toEntity())
}