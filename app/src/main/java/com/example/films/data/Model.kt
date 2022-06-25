package com.example.films.data

import com.example.test1.Results


data class MovieModel(
    val title:String?,
    val image:String?,
     val description:String?)
val Results.toModel:MovieModel
get() = MovieModel(
  title = displayTitle,
  image=multimedia?.src,
    description = summaryShort
)

