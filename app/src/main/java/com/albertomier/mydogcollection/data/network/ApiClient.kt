package com.albertomier.mydogcollection.data.network

import com.albertomier.mydogcollection.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiClient {
    @GET("dogs")
    suspend fun getAllDogs(): Response<DogListApiResponse>

    @GET("sign_up")
    suspend fun signUp(@Body signUpBody: SignUpBody): Response<AuthApiResponse>

    @GET("sign_in")
    suspend fun signIn(@Body signInBody: SignInBody): Response<AuthApiResponse>
}