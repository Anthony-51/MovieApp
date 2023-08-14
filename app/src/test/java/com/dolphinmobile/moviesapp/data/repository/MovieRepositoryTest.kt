package com.dolphinmobile.moviesapp.data.repository

import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import com.dolphinmobile.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryTest: MovieRepository {

    private val movies = mutableListOf<MovieEntity>()

    override suspend fun getMovies(): Response<List<MovieDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieById(movieId: Int): Response<MovieDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun addMovie(movie: MovieEntity) {
        movies.add(movie)
    }

    override suspend fun removeMovie(movie: MovieEntity) {
        movies.remove(movie)
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return flow { emit(movies) }
    }

    override suspend fun getFavoriteMovieById(movieId: Int): MovieEntity? {
        return movies.find { it.id == movieId }
    }
}