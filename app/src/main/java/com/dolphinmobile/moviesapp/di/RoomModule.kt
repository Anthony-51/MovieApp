package com.dolphinmobile.moviesapp.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import com.dolphinmobile.moviesapp.data.local.MoviesDB
import com.dolphinmobile.moviesapp.util.Constants

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

      @Singleton
      @Provides
      fun provideMovieDB(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                  context, MoviesDB::class.java, Constants.DB_NAME
            )

      @Singleton
      @Provides
      fun provideMovieDao(db: MoviesDB) = db.getMovieDao()

}