package com.example.films.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.films.data.MovieModel
import com.example.films.data.toModel
import retrofit2.HttpException


class MoviesPagingSource ( private  val moviesApi: MoviesApi): PagingSource<Int, MovieModel>()  {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        val position = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(position) ?: return null
        return page.prevKey?.plus(20) ?: page.nextKey?.minus(20)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,MovieModel > {
        try {
            val pageNumber = params.key ?: 0
            val response =moviesApi.getFilms(pageNumber)
            return if (response.isSuccessful) {
                val films = response.body()!!.results.map { it.toModel }
                val nextPage = if (films.isEmpty()) null else pageNumber + 20
                val prevPage = if (pageNumber > 20) pageNumber - 20 else null
                LoadResult.Page(films, prevPage, nextPage)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}