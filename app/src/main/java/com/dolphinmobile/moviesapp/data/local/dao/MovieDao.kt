package com.dolphinmobile.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

      @Query("SELECT * FROM movies")
      fun getMovies(): List<MovieEntity>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
       fun addMovie(movie: MovieEntity)

      @Delete
      fun removeMovie(movie: MovieEntity)
}