package com.albertomier.mydogcollection.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.mydogcollection.databinding.DogListItemBinding
import com.albertomier.mydogcollection.domain.model.Dog

class DogAdapter(private var dogList: List<Dog>, private val onClickListener: (Dog) -> Unit) :
    RecyclerView.Adapter<DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(LayoutInflater.from(parent.context))
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogList[position]
        holder.bind(dog, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!dogList.isNullOrEmpty()) {
            return dogList.size
        }
        return 0
    }
}