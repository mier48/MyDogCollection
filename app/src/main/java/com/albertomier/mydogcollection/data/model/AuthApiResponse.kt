package com.albertomier.mydogcollection.data.model

import com.squareup.moshi.Json

data class AuthApiResponse(
    val message: String,
    @field:Json(name = "is_success") val isSuccess: Boolean,
    val data: UserResponse
)