package com.dolphinmobile.moviesapp.data.repository

import com.dolphinmobile.moviesapp.data.repository.data_source.MovieDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
      private val dataSource: MovieDataSource
){
      suspend fun  getMovies() = dataSource.getMovies()

      suspend fun getMovieById(movieId: Int) = dataSource.getMovieById(movieId)
}