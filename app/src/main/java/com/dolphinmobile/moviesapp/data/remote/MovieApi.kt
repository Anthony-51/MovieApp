package com.dolphinmobile.moviesapp.data.remote

import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import com.dolphinmobile.moviesapp.data.remote.dto.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

      @GET("movie/popular")
      suspend fun getMovies(): MoviesResponse

      @GET("movie/{movie_id}")
      suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDTO
}