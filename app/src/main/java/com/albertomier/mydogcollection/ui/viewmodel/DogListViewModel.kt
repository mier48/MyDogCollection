package com.albertomier.mydogcollection.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.domain.GetDogs
import com.albertomier.mydogcollection.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogs: GetDogs
) : ViewModel() {

    private val _dogList = MutableLiveData<List<Dog>>()
    val dogList: LiveData<List<Dog>> get() = _dogList

    private val _status = MutableLiveData<ApiResponseStatus<List<Dog>>>()
    val status: LiveData<ApiResponseStatus<List<Dog>>> get() = _status

    init {
        downloadDogs()
    }

    private fun downloadDogs() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getDogs())
        }
    }

    private fun handleResponseStatus(responseStatus: ApiResponseStatus<List<Dog>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _dogList.value = responseStatus.data
        }

        _status.value = responseStatus
    }
}