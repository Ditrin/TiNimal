package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class CatDTO (
    val id: Long,
    val name: String?,
    val sex: Boolean,
    val breed: String?,
    val age: Int?,
    val price: Int?,
    val passport: Boolean,
    val vaccination: Boolean,
    val certificates: Boolean,
    val info: String?,
    val photo: String?
)