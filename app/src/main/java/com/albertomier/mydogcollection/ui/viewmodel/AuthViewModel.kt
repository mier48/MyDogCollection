package com.albertomier.mydogcollection.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.domain.SignIn
import com.albertomier.mydogcollection.domain.SignUp
import com.albertomier.mydogcollection.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUp: SignUp,
    private val signIn: SignIn
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _status = MutableLiveData<ApiResponseStatus<User>>()
    val status: LiveData<ApiResponseStatus<User>> get() = _status

    fun toSignUp(email: String, password: String, passwordConfirmation: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(signUp(email, password, passwordConfirmation))
        }
    }

    fun toSignIn(email: String, password: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(signIn(email, password))
        }
    }

    private fun handleResponseStatus(responseStatus: ApiResponseStatus<User>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _user.value = responseStatus.data
        }

        _status.value = responseStatus
    }
}