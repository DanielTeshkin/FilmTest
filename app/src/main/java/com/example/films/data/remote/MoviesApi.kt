package com.example.films.data.remote


import com.example.test1.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("./v2/reviews/all.json?api-key=$apiKey")
    suspend fun getFilms(@Query("offset") offset:Int): Response<Movies>

    companion object{
        const val apiKey="XtLoB0yYgaqsG8Rbgt34K4EQIYxjy7IX"
    }
}

