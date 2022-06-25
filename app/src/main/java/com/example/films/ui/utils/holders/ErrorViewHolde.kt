package com.example.films.ui.utils.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.example.films.ui.utils.MoviesLoaderStateAdapter
import com.example.test1.databinding.EndItemBinding

class EndViewHolder internal constructor(
    private val binding: EndItemBinding
) : MoviesLoaderStateAdapter.ItemViewHolder(binding.root) {

    override fun bind(loadState: LoadState) {
        require(loadState is LoadState.Error)
        binding.errorMessage.text = loadState.error.localizedMessage
    }

    companion object {

        operator fun invoke(
            layoutInflater: LayoutInflater,
            parent: ViewGroup? = null,
            attachToRoot: Boolean = false
        ): EndViewHolder {
            return EndViewHolder(
                EndItemBinding.inflate(
                    layoutInflater,
                    parent,
                    attachToRoot
                )
            )
        }
    }
}