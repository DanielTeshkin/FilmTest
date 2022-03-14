package com.example.test1.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnect {
    private val URL: String = "https://api.nytimes.com/svc/movies/"
    private fun Retrofit(): Retrofit {
        val log = HttpLoggingInterceptor()
        log.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(log).build()
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient).build()
    }
    fun create(): ApiConnect? {
        return Retrofit().create(ApiConnect::class.java)
    }
}