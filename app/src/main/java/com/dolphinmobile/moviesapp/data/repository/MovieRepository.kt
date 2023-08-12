package com.dolphinmobile.moviesapp.data.repository

import com.dolphinmobile.moviesapp.data.local.dao.MovieDao
import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.repository.data_source.MovieDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
      private val dataSource: MovieDataSource,
//      private val dao: MovieDao
){
      suspend fun  getMovies() = dataSource.getMovies()

      suspend fun getMovieById(movieId: Int) = dataSource.getMovieById(movieId)

//      fun addMovie(movie: MovieEntity) = dao.addMovie(movie)
//
//      fun removeMovie(movie: MovieEntity) = dao.removeMovie(movie)
//
//      fun getFavoriteMovies() = dao.getMovies()
}