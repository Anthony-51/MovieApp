package com.dolphinmobile.moviesapp.data.repository.data_source

import android.util.Log
import com.dolphinmobile.moviesapp.data.remote.MovieApi
import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.remote.dto.ErrorResponse401
import com.dolphinmobile.moviesapp.data.remote.dto.ErrorResponse422
import com.dolphinmobile.moviesapp.data.remote.dto.MovieDTO
import com.google.gson.Gson
import org.json.JSONObject
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
                  return if (e.code() == 422){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse422::class.java)
                        Response.Error(response.errors?.get(0) ?: "An unexpected error occured", e.code())
                  } else if (e.code() == 401){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse401::class.java)
                        Response.Error(response.message, e.code())
                  } else Response.Error(e.localizedMessage ?: "An unexpected error occured", e.code())
            } catch (e: IOException) {
                  Response.Error("No se pudo conectar con el servidor. Verifique su conexión",1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: e.toString(), 1)
            }
      }

      suspend fun getMovieById(movieId: Int): Response<MovieDTO> {
            return try {
                  val response = api.getMovieById(movieId)
                  Response.Success(response)
            } catch (e: HttpException) {
                  return if (e.code() == 422){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse422::class.java)
                        Response.Error(response.errors?.get(0) ?: "An unexpected error occured", e.code())
                  } else if (e.code() == 401){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse401::class.java)
                        Response.Error(response.message, e.code())
                  } else Response.Error(e.localizedMessage ?: "An unexpected error occured", e.code())
            } catch (e: IOException) {
                  Response.Error("No se pudo conectar con el servidor. Verifique su conexión",1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: e.toString(), 1)
            }
      }
}