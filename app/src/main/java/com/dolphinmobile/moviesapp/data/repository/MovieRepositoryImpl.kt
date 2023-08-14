package com.dolphinmobile.moviesapp.data.repository

import com.dolphinmobile.moviesapp.data.local.dao.MovieDao
import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import com.dolphinmobile.moviesapp.data.repository.data_source.MovieDataSource
import com.dolphinmobile.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
      private val dataSource: MovieDataSource,
      private val dao: MovieDao
): MovieRepository {
      override suspend fun getMovies(): Response<List<MovieDTO>> {
            return dataSource.getMovies()
      }

      override suspend fun getMovieById(movieId: Int): Response<MovieDTO> {
            return dataSource.getMovieById(movieId)
      }

      override suspend fun addMovie(movie: MovieEntity) {
            return dao.addMovie(movie)
      }

      override suspend fun removeMovie(movie: MovieEntity) {
            return dao.removeMovie(movie)
      }

      override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
            return dao.getMovies()
      }

      override suspend fun getFavoriteMovieById(movieId: Int): MovieEntity? {
            return dao.getMovieById(movieId)
      }
}