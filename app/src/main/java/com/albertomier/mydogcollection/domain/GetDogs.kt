package com.albertomier.mydogcollection.domain

import com.albertomier.mydogcollection.data.DogRepository
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.domain.model.Dog
import javax.inject.Inject

class GetDogs @Inject constructor(private val repository: DogRepository) {
    suspend operator fun invoke(): ApiResponseStatus<List<Dog>> {
        return repository.downloadDogs()
    }
}