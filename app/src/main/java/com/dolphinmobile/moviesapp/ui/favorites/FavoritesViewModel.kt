package com.dolphinmobile.moviesapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dolphinmobile.moviesapp.domain.use_case.GetFavoritesMovies
import com.dolphinmobile.moviesapp.ui.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavoritesMovies
) : ViewModel() {

    private val _favoriteState = MutableLiveData(FavoriteState())
    val favoriteState: LiveData<FavoriteState> get() = _favoriteState

    init {
        getFavoritesMovies()
    }
    private fun getFavoritesMovies() {
        _favoriteState.value = FavoriteState(isLoading = true)
        getFavorites().onEach { movies ->
            if (movies.isNotEmpty()) {
                _favoriteState.value = _favoriteState.value?.copy(movies = movies, isLoading = false)
            }else{
                _favoriteState.value = _favoriteState.value?.copy(error = "No tiene favoritos guardados", isLoading = false)
            }
        }
            .launchIn(viewModelScope)
    }
}