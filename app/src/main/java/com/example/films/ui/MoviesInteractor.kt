package com.example.films.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.films.data.MovieModel
import com.example.films.data.remote.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesPagingSource: MoviesPagingSource) {

    fun getMovies(pagingConfig: PagingConfig = getDefaultPageConfig(),
    ): Flow<PagingData<MovieModel>> = Pager(config = pagingConfig, pagingSourceFactory = { moviesPagingSource }).flow

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = false)
    }
}