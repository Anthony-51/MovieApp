package com.dolphinmobile.moviesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dolphinmobile.moviesapp.domain.model.Movie
import com.dolphinmobile.moviesapp.domain.use_case.AddMovieDB
import com.dolphinmobile.moviesapp.domain.use_case.GetMovie
import com.dolphinmobile.moviesapp.domain.use_case.RemoveMovieDB
import com.dolphinmobile.moviesapp.ui.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
      private val addMovieDB: AddMovieDB,
      private val getMovieById: GetMovie,
      private val removeMovie: RemoveMovieDB
) : ViewModel() {
      private val _detailState = MutableLiveData(DetailState())
      val detailState: LiveData<DetailState> get() = _detailState

      fun getMovieBy(movieId: Int) {
            getMovieById(movieId).onEach { result ->
                  when (result) {
                        is ResourceState.Loading -> {
                              _detailState.value = DetailState(isLoading = true)
                        }

                        is ResourceState.Success -> {
                              _detailState.value = _detailState.value?.copy(movie = result.data, isLoading = false)
                        }

                        is ResourceState.Error -> {
                              _detailState.value = _detailState.value?.copy(error = result.message, isLoading = false)
                        }
                  }
            }
                  .launchIn(viewModelScope)
      }

      fun addMovie() {
            viewModelScope.launch {
                  addMovieDB(_detailState.value!!.movie!!)
            }
      }

      fun removeMovie() {
            viewModelScope.launch {
                  removeMovie(_detailState.value!!.movie!!)
            }
      }

}