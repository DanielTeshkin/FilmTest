package com.example.films.ui.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.films.ui.utils.holders.EndViewHolder
import com.example.films.ui.utils.holders.ProgressViewHolder
import com.example.test1.databinding.ProgressItemBinding


class MoviesLoaderStateAdapter : LoadStateAdapter<MoviesLoaderStateAdapter.ItemViewHolder>() {
    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        LoadState.Loading -> LOAD
        is LoadState.Error -> ERROR
    }

    private companion object {
        private const val ERROR = 1
        private const val LOAD = 0
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when(loadState) {
            LoadState.Loading -> ProgressViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> EndViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("Not supported")
        }
    }

}

