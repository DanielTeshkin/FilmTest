package com.example.films.di


import androidx.viewbinding.BuildConfig
import com.example.films.data.remote.MoviesApi
import com.example.films.data.remote.MoviesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    @Provides
    fun provideHttpClient():OkHttpClient  {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY )
        return  OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()}

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)

    @Provides
    fun providePagingSource(moviesApi: MoviesApi)=MoviesPagingSource(moviesApi)
}