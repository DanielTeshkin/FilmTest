package com.example.test1.logic

import com.example.test1.DataClasses.Model
import com.example.test1.Models
import com.example.test1.retrofit.RetrofitConnect
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseFilm {

  fun response(x:Int) :ArrayList<Model> {
       val h = ArrayList<Model>()
        val image = RetrofitConnect().create()?.getAllData(x)
        with(image) {
            this?.enqueue(object : Callback<Models> {
                override fun onResponse(
                    call: Call<Models>,
                    response: Response<Models>
                ) {
                    if (response.isSuccessful) {
                        val models = response.body()!!
                        val mod = models.results
                        for (i in 0..(mod.size - 1)) {
                            val a = mod[i].headline
                            val b = mod[i].summaryShort
                            val c = mod[i].multimedia?.src
                            val g = Model(a, b, c)
                            h.add(g)
                        }
                    }
                }

                override fun onFailure(call: Call<Models>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
       return h
  }
}