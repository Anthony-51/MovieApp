package com.dolphinmobile.moviesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
      @PrimaryKey
      val id: Int,
      @ColumnInfo("movie_id")
      val title: String,
      @ColumnInfo("movie_poster_path")
      val posterPath: String,
      @ColumnInfo("movie_release_date")
      val releaseDate: String
)
