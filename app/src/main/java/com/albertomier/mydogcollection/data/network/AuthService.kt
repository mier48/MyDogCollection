package com.albertomier.mydogcollection.data.network

import com.albertomier.mydogcollection.data.model.AuthApiResponse
import com.albertomier.mydogcollection.data.model.SignInBody
import com.albertomier.mydogcollection.data.model.SignUpBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor(private val api: ApiClient) {

    suspend fun signUp(signUpBody: SignUpBody): AuthApiResponse {
        return withContext(Dispatchers.IO) {
            api.signUp(signUpBody).body()!!
        }
    }

    suspend fun signIn(signInBody: SignInBody): AuthApiResponse {
        return withContext(Dispatchers.IO) {
            api.signIn(signInBody).body()!!
        }
    }
}