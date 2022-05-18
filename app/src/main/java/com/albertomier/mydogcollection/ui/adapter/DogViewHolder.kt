package com.albertomier.mydogcollection.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.albertomier.mydogcollection.databinding.DogListItemBinding
import com.albertomier.mydogcollection.domain.model.Dog

class DogViewHolder(private val binding: DogListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dog: Dog, onClickListener: (Dog) -> Unit) {
        binding.dogImage.load(dog.imageUrl)

        itemView.setOnClickListener {
            onClickListener(dog)
        }
    }
}