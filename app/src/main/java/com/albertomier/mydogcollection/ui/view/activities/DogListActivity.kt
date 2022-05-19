package com.albertomier.mydogcollection.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.albertomier.mydogcollection.core.DOG_KEY
import com.albertomier.mydogcollection.core.GRID_SPAN_COUNT
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.databinding.ActivityDogListBinding
import com.albertomier.mydogcollection.domain.model.Dog
import com.albertomier.mydogcollection.ui.adapter.DogAdapter
import com.albertomier.mydogcollection.ui.viewmodel.DogListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogListBinding
    private val dogListViewModel: DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = GridLayoutManager(this, GRID_SPAN_COUNT)
        binding.dogList.layoutManager = manager

        dogListViewModel.dogList.observe(this) { dogList ->
            binding.dogList.adapter = DogAdapter(dogList) { dog -> onDogSelected(dog) }
        }

        dogListViewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.progress.isVisible = false
                    Toast.makeText(this, getString(status.messageId), Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> binding.progress.isVisible = true
                is ApiResponseStatus.Success -> binding.progress.isVisible = false
            }
        }
    }

    private fun onDogSelected(dog: Dog) {
        val intent = Intent(this, DogDetailActivity::class.java)
        intent.putExtra(DOG_KEY, dog)
        startActivity(intent)
    }
}