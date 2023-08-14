package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.domain.model.toDomain
import com.dolphinmobile.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritesMovies @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getFavoriteMovies().map { movies ->
            movies.map { movie -> movie.toDomain() }
        }
}