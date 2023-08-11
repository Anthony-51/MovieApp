package com.dolphinmobile.moviesapp.data.remote

sealed class Response<out T : Any> {
      data class Success<out T : Any>(val data: T) : Response<T>()
      data class Error(val msg: String, val code: Int) : Response<Nothing>()
}
