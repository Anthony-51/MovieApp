package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.repository.MovieRepositoryTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetFavoritesMoviesTest {

    private lateinit var getFavoritesMovies: GetFavoritesMovies
    private lateinit var repositoryTest: MovieRepositoryTest

    @Before
    fun setUp() {
        repositoryTest = MovieRepositoryTest()
        getFavoritesMovies = GetFavoritesMovies(repositoryTest)


    }

    @Test
    fun validateThatMoviesIsEmpty() = runBlocking {
        val movies = getFavoritesMovies().first()
        assertTrue(movies.isEmpty())
    }

    @Test
    fun validateThatMoviesIsNotEmpty() = runBlocking {
        repositoryTest.addMovie(MovieEntity(1, "Movie", "/movie.jpg","01/01/2021"))
        repositoryTest.addMovie(MovieEntity(2, "Movie", "/movie.jpg","01/01/2021"))
        repositoryTest.addMovie(MovieEntity(3, "Movie", "/movie.jpg","01/01/2021"))
        val movies = getFavoritesMovies().first()
        assertFalse(movies.isEmpty())
    }
}