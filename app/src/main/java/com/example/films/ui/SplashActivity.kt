package com.example.films.ui

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.test1.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        lifecycleScope.launch {
              delay(2000)
              openMovies() }
    }

  private fun openMovies(){
        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }

}