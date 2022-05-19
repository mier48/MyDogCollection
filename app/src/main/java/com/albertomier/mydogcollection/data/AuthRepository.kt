package com.albertomier.mydogcollection.data

import com.albertomier.mydogcollection.data.model.SignInBody
import com.albertomier.mydogcollection.data.model.SignUpBody
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.data.network.AuthService
import com.albertomier.mydogcollection.data.network.makeNetworkCall
import com.albertomier.mydogcollection.domain.model.User
import com.albertomier.mydogcollection.domain.model.toDomain
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: AuthService
) {

    suspend fun signUp(
        email: String,
        password: String,
        passwordConfirmation: String
    ): ApiResponseStatus<User> {
        val signUpBody = SignUpBody(email, password, passwordConfirmation)
        return makeNetworkCall {
            val response = service.signUp(signUpBody)

            if (!response.isSuccess) {
                throw Exception(response.message)
            }

            response.data.toDomain()
        }
    }

    suspend fun signIn(
        email: String,
        password: String
    ): ApiResponseStatus<User> {
        val signInBody = SignInBody(email, password)
        return makeNetworkCall {
            val response = service.signIn(signInBody)

            if (!response.isSuccess) {
                throw Exception(response.message)
            }

            response.data.toDomain()
        }
    }
}