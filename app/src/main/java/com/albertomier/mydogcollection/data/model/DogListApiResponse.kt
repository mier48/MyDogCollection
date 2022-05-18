package com.albertomier.mydogcollection.data.model

import com.squareup.moshi.Json

data class DogListApiResponse(
    val message: String,
    @field:Json(name = "is_success") val isSuccess: Boolean,
    val data: DogListResponse
)