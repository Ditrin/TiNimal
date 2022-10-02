package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatAddDTO(
    @field:Json(name = "sex") val sex: Boolean,
    @field:Json(name = "breed") val breed: String,
    @field:Json(name = "age") val name: Int,
    @field:Json(name = "price") val price: Int,
    @field:Json(name = "passport") val passport: Boolean,
    @field:Json(name = "vaccination") val vaccination: Boolean,
    @field:Json(name = "certificates") val certificates: Boolean,
    @field:Json(name = "info") val info: String,
    @field:Json(name = "photo") val photo: String
)