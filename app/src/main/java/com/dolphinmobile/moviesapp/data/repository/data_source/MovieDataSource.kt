package com.dolphinmobile.moviesapp.data.repository.data_source

import com.dolphinmobile.moviesapp.data.remote.MovieApi
import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDataSource @Inject constructor(
      private val api: MovieApi
){

      suspend fun getMovies(): Response<List<MovieDTO>> {
            return try {
                  val response = api.getMovies()
                  Response.Success(response.results)
            } catch (e: HttpException) {
                  Response.Error(e.localizedMessage ?: "An unexpected error occured", 1)
            } catch (e: IOException) {
                  Response.Error("Couldn't reach server. Check your internet connection.",1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: e.toString(), 1)
            }
      }

      suspend fun getMovieById(movieId: Int): Response<MovieDTO> {
            return try {
                  val response = api.getMovieById(movieId)
                  Response.Success(response)
            } catch (e: HttpException) {
                  Response.Error(e.localizedMessage ?: "An unexpected error occured", 1)
            } catch (e: IOException) {
                  Response.Error("Couldn't reach server. Check your internet connection.",1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: e.toString(), 1)
            }
      }
}