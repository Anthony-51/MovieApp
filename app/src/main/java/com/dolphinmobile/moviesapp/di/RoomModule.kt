package com.dolphinmobile.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.dolphinmobile.moviesapp.data.local.MoviesDB
import com.dolphinmobile.moviesapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

      @Singleton
      @Provides
      fun provideRoom(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                  context, MoviesDB::class.java, Constants.DB_NAME
            )
                  .build()

      @Singleton
      @Provides
      fun provideMovieDao(db: MoviesDB) = db.getMovieDao()

}