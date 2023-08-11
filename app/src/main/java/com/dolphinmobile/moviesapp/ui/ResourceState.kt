package com.dolphinmobile.moviesapp.ui

sealed class ResourceState<out T : Any>{
      object Loading: ResourceState<Nothing>()
      data class Success<out T : Any>(val data: T): ResourceState<T>()
      data class Error(val message: String, val code: Int): ResourceState<Nothing>()
}
