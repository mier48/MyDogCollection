package com.albertomier.mydogcollection.data.network

sealed class DogApiResponseStatus<T>() {
    class Loading<T>() : DogApiResponseStatus<T>()
    class Error<T>(val messageId: Int) : DogApiResponseStatus<T>()
    class Success<T>(val data: T) : DogApiResponseStatus<T>()
}