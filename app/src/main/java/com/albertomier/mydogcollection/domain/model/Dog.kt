package com.albertomier.mydogcollection.domain.model

import android.os.Parcelable
import com.albertomier.mydogcollection.data.model.DogResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val id: Long,
    val index: Int,
    val type: String,
    val name: String,
    val heightFemale: String,
    val heightMale: String,
    val imageUrl: String,
    val lifeExpectancy: String,
    val temperament: String,
    val weightFemale: String,
    val weightMale: String
) : Parcelable

fun DogResponse.toDomain() =
    Dog(
        id,
        index,
        type,
        name,
        heightFemale,
        heightMale,
        imageUrl,
        lifeExpectancy,
        temperament,
        weightFemale,
        weightMale
    )