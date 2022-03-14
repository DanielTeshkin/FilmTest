package com.example.test1

import android.content.Intent

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
 lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler= Handler()
        handler.postDelayed({
         var intent=Intent(this,MainActivity2::class.java)
         startActivity(intent)
        },3000)
    }

}