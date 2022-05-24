package com.example.ryckandmorty.ui.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.databinding.ItemCharactersBinding
import com.example.ryckandmorty.utils.bindingadapters.loadImage
import javax.inject.Inject

class CharactersAdapter @Inject constructor() :
    PagingDataAdapter<Characters, CharactersAdapter.ViewHolder>(DiffUtilCallBack) {

    class ViewHolder(val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.apply {
                this.imageCharacter.loadImage(it.photo)
                this.name.text = it.name
                this.status.text = it.status
                this.species.text = it.species
                this.gender.text = it.gender
                this.origin.text = it.origin.name
                this.location.text = it.location.name
            }
        }

    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }
    }
}