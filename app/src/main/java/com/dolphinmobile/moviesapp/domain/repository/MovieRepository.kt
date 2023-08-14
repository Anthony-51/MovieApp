package com.dolphinmobile.moviesapp.domain.repository

import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Response<List<MovieDTO>>

    suspend fun getMovieById(movieId: Int): Response<MovieDTO>

    suspend fun addMovie(movie: MovieEntity)

    suspend fun removeMovie(movie: MovieEntity)

    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    suspend fun getFavoriteMovieById(movieId: Int): MovieEntity?
}