package com.albertomier.mydogcollection.data.network

import com.albertomier.mydogcollection.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): DogApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        DogApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        DogApiResponseStatus.Error(R.string.unknow_host_exception_error)
    } catch (e: Exception) {
        DogApiResponseStatus.Error(R.string.unknow_error)
    }
}