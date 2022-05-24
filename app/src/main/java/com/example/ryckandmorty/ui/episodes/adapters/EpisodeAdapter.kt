package com.example.ryckandmorty.ui.episodes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.databinding.ItemCharactersBinding
import com.example.ryckandmorty.databinding.ItemEpisodeBinding
import com.example.ryckandmorty.utils.bindingadapters.loadImage
import javax.inject.Inject

class EpisodeAdapter @Inject constructor() :
    PagingDataAdapter<Episode, EpisodeAdapter.ViewHolder>(DiffUtilCallBack) {

    class ViewHolder(val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.apply {
                this.name.text = it.name
                this.airDate.text = it.airDate
                this.episode.text = it.episode
            }
        }

    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }
}