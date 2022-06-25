package com.example.films.ui.utils.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.example.films.ui.utils.MoviesLoaderStateAdapter
import com.example.test1.databinding.ProgressItemBinding

class ProgressViewHolder internal constructor(
    private val binding: ProgressItemBinding
) : MoviesLoaderStateAdapter.ItemViewHolder(binding.root) {

    override fun bind(loadState: LoadState) {

    }

    companion object {

        operator fun invoke(
            layoutInflater: LayoutInflater,
            parent: ViewGroup? = null,
            attachToRoot: Boolean = false
        ): ProgressViewHolder {
            return ProgressViewHolder(
                ProgressItemBinding.inflate(
                    layoutInflater,
                    parent,
                    attachToRoot
                )
            )
        }
    }
}