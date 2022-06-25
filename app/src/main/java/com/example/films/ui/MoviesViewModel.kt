package com.example.films.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesInteractor: MoviesInteractor) :ViewModel(){

    val movies = moviesInteractor.getMovies().cachedIn(viewModelScope)
}