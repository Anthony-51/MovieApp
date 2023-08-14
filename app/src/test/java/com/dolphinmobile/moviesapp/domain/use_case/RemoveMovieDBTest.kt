package com.dolphinmobile.moviesapp.domain.use_case

import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import com.dolphinmobile.moviesapp.data.repository.MovieRepositoryTest
import com.dolphinmobile.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class RemoveMovieDBTest {

    private lateinit var removeMovieDB: RemoveMovieDB
    private lateinit var repositoryTest: MovieRepositoryTest

    @Before
    fun setUp() {
        repositoryTest = MovieRepositoryTest()
        removeMovieDB = RemoveMovieDB(repositoryTest)
    }

    @Test
    fun validateThatMovieIsRemoved() = runBlocking {
        repositoryTest.addMovie(MovieEntity(1, "Movie", "/movie.jpg","01/01/2021"))
        removeMovieDB(Movie(1, "Movie", "Movie description","/movie.jpg", "01/01/2021",4.5, true, emptyList()))
        val movies = repositoryTest.getFavoriteMovies().first()
        assertEquals(0, movies.size)
    }

    @Test
    fun validateThatMovieDoesNotExist() = runBlocking {
        removeMovieDB(Movie(1, "Movie", "Movie description","/movie.jpg", "01/01/2021",4.5, true, emptyList()))
        val movie = repositoryTest.getFavoriteMovieById(1)
        assertNull(movie)
    }

}