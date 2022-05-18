package com.albertomier.mydogcollection.data

import com.albertomier.mydogcollection.data.network.DogApiResponseStatus
import com.albertomier.mydogcollection.data.network.DogService
import com.albertomier.mydogcollection.data.network.makeNetworkCall
import com.albertomier.mydogcollection.domain.model.Dog
import com.albertomier.mydogcollection.domain.model.toDomain
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val api: DogService
) {

    suspend fun downloadDogs(): DogApiResponseStatus<List<Dog>> =
        makeNetworkCall { api.getAllDogs().map { it.toDomain() } }
}