package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.data.repository.MovieRepositoryTest
import com.dolphinmobile.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddMovieDBTest{
    private lateinit var addMovieDB: AddMovieDB
    private lateinit var repositoryTest: MovieRepositoryTest
    @Before
    fun setUp() {
        repositoryTest = MovieRepositoryTest()
        addMovieDB = AddMovieDB(repositoryTest)
    }

    @Test
    fun validateThatMovieIsAdded() = runBlocking {
        addMovieDB(Movie(1, "Movie", "Movie description","/movie.jpg", "01/01/2021",4.5, true, emptyList()))
        val movies = repositoryTest.getFavoriteMovies().first()
        assertEquals(1, movies.size)
    }

    @Test
    fun validateThatMovieExists() = runBlocking {
        addMovieDB(Movie(1, "Movie", "Movie description","/movie.jpg", "01/01/2021",4.5, true, emptyList()))
        val movie = repositoryTest.getFavoriteMovieById(1)
        assertNotNull(movie)
    }

    @Test
    fun validateThatMovieDoesNotExist() = runBlocking {
        addMovieDB(Movie(1, "Movie", "Movie description","/movie.jpg", "01/01/2021",4.5, true, emptyList()))
        val movie = repositoryTest.getFavoriteMovieById(2)
        assertNull(movie)
    }

}