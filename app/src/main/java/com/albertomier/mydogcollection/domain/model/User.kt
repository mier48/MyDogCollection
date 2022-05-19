package com.albertomier.mydogcollection.domain.model

import com.albertomier.mydogcollection.MyDogCollectionApp
import com.albertomier.mydogcollection.core.AUTH_TOKEN_KEY
import com.albertomier.mydogcollection.core.EMAIL_KEY
import com.albertomier.mydogcollection.core.ID_KEY
import com.albertomier.mydogcollection.data.model.UserResponse

data class User(val id: Long, val email: String, val authenticationToken: String) {

    companion object {
        fun setLoggedInUser(user: User) {
            MyDogCollectionApp.preference.also {
                it.setLong(ID_KEY, user.id)
                it.setString(EMAIL_KEY, user.email)
                it.setString(AUTH_TOKEN_KEY, user.authenticationToken)
            }
        }

        fun getLoggedInUser(): User? {
            return if (MyDogCollectionApp.preference.getLong(ID_KEY) == 0L) {
                null
            } else {
                User(
                    MyDogCollectionApp.preference.getLong(ID_KEY),
                    MyDogCollectionApp.preference.getString(EMAIL_KEY),
                    MyDogCollectionApp.preference.getString(AUTH_TOKEN_KEY)
                )
            }
        }
    }
}

fun UserResponse.toDomain() = User(id, email, authenticationToken)
