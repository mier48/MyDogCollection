package com.albertomier.mydogcollection.domain

import com.albertomier.mydogcollection.data.AuthRepository
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.domain.model.User
import javax.inject.Inject

class SignIn @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): ApiResponseStatus<User> =
        repository.signIn(email, password)

}