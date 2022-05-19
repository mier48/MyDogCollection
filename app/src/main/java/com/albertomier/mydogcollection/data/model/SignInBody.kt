package com.albertomier.mydogcollection.data.model

import com.squareup.moshi.Json

data class SignInBody(
    val email: String,
    val password: String,
)