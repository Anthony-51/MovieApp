package com.dolphinmobile.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

      @Query("SELECT * FROM movies")
      fun getMovies(): Flow<List<MovieEntity>>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
       suspend fun addMovie(movie: MovieEntity)

      @Delete
      suspend fun removeMovie(movie: MovieEntity)

      @Query("SELECT * FROM movies WHERE id = :movieId")
      suspend fun getMovieById(movieId: Int): MovieEntity?
}