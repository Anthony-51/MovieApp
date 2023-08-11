package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.data.remote.Response
import com.dolphinmobile.moviesapp.data.repository.MovieRepository
import com.dolphinmobile.moviesapp.domain.model.toDomain
import com.dolphinmobile.moviesapp.ui.ResourceState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovies @Inject constructor(
      private val repository: MovieRepository
){
      operator fun invoke() = flow {
            emit(ResourceState.Loading)
            when (val response = repository.getMovies()){
                  is Response.Success -> {
                        emit(ResourceState.Success(response.data.map { it.toDomain() }))
                  }
                  is Response.Error -> {
                        emit(ResourceState.Error(response.msg, response.code))
                  }
            }
      }
}