package com.dolphinmobile.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dolphinmobile.moviesapp.data.local.dao.MovieDao
import com.dolphinmobile.moviesapp.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDB: RoomDatabase() {
      abstract fun getMovieDao(): MovieDao
}