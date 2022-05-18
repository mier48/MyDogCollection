package com.albertomier.mydogcollection.data.network

import com.albertomier.mydogcollection.data.model.DogListApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiClient {
    @GET("dogs")
    suspend fun getAllDogs(): Response<DogListApiResponse>
}