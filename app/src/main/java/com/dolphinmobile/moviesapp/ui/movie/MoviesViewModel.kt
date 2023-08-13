package com.dolphinmobile.moviesapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dolphinmobile.moviesapp.domain.use_case.GetMovies
import com.dolphinmobile.moviesapp.ui.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
      private val getMovies: GetMovies
) : ViewModel(){
      private val _movieState = MutableLiveData(MovieState())
      val movieState: LiveData<MovieState> get() = _movieState

      init {
            getAllMovies()
      }

      private fun getAllMovies() {
            getMovies().onEach { result ->
                  when (result) {
                        is ResourceState.Success -> {
                              _movieState.value = _movieState.value!!.copy(movies = result.data)
                        }
                        is ResourceState.Error -> {
                              _movieState.value = MovieState(error = result.message)
                        }
                        is ResourceState.Loading -> {
                              _movieState.value = MovieState(isLoading = true)
                        }
                  }
            }
                  .launchIn(viewModelScope)
      }

      fun filterMovies(query: String){
            if (_movieState.value!!.movies.isNotEmpty()){
                  val movies = _movieState.value!!.movies.filter {
                        it.title.lowercase().contains(query.lowercase(), ignoreCase = true)
                  }
                  if (movies.isNotEmpty()){
                        _movieState.value = _movieState.value!!.copy(filterMovies = movies, error = "")
                  }else{
                        _movieState.value = _movieState.value!!.copy(error = "No hay resultados para :\n$query")
                  }
            }
      }

      fun resetMovies(){
            _movieState.value = _movieState.value!!.copy(filterMovies = emptyList(), error = "")
      }
}