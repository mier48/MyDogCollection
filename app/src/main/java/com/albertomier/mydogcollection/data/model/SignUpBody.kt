package com.albertomier.mydogcollection.data.model

import com.squareup.moshi.Json

data class SignUpBody(
    val email: String,
    val password: String,
    @field:Json(name = "password_confirmation") val passwordConfirmation: String
)