package com.albertomier.mydogcollection.data.model

import com.albertomier.mydogcollection.domain.model.User
import com.squareup.moshi.Json

data class UserResponse(
    val id: Long,
    val email: String,
    @field:Json(name = "authentication_token") val authenticationToken: String
)