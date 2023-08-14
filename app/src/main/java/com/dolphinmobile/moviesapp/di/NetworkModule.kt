package com.dolphinmobile.moviesapp.di

import com.dolphinmobile.moviesapp.data.local.dao.MovieDao
import com.dolphinmobile.moviesapp.util.Constants
import com.dolphinmobile.moviesapp.data.remote.MovieApi
import com.dolphinmobile.moviesapp.data.repository.MovieRepositoryImpl
import com.dolphinmobile.moviesapp.data.repository.data_source.MovieDataSource
import com.dolphinmobile.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

      @Provides
      @Singleton
      fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                  .baseUrl(Constants.BASE_URL)
                  .client(provideHttpClient())
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
      }

      @Provides
      @Singleton
      fun provideMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
      }

      @Provides
      @Singleton
      fun provideMovieRepository(ds: MovieDataSource, movieDao: MovieDao): MovieRepository {
            return MovieRepositoryImpl(ds, movieDao)
      }

      private fun provideHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                  .addInterceptor {
                        val request = it.request().newBuilder().addHeader("Authorization", "Bearer ${Constants.API_KEY}").build()
                        it.proceed(request)
                  }
                  .connectTimeout(1, TimeUnit.MINUTES)
                  .readTimeout(1, TimeUnit.MINUTES)
                  .writeTimeout(1, TimeUnit.MINUTES)
                  .build()
      }
}