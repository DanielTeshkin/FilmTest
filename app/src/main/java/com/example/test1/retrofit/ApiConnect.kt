package com.example.test1.retrofit

import com.example.test1.Models
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiConnect {
    @GET("./v2/reviews/all.json?api-key=XtLoB0yYgaqsG8Rbgt34K4EQIYxjy7IX")
    fun getAllData(@Query("offset") offset:Int): Call<Models>
}