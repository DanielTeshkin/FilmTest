package com.example.films.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.films.data.MovieModel
import com.example.test1.databinding.FilmItemBinding

class MoviesAdapter(private val context: Context): PagingDataAdapter<MovieModel, MoviesAdapter.MoviesViewHolder>(MOVIES_DIFF_UTIL) {
    inner class MoviesViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

                    fun bind(model: MovieModel){
                        binding.apply {
                            title.text = model.title
                            discription.text=model.description
                            Glide.with(context).load(model.image).circleCrop().into(image)
                        }

                    }

        }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            FilmItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }
    companion object {
        private const val PAGE_PADDING = 20

        private val MOVIES_DIFF_UTIL = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem == newItem
        }
    }
}