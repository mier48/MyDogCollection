package com.albertomier.mydogcollection.data.network

import com.albertomier.mydogcollection.data.model.DogListApiResponse
import com.albertomier.mydogcollection.data.model.DogResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DogService @Inject constructor(private val api: DogApiClient) {

    suspend fun getAllDogs(): List<DogResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<DogListApiResponse> = api.getAllDogs()
            response.body()?.data?.dogs ?: emptyList()
        }
    }
}