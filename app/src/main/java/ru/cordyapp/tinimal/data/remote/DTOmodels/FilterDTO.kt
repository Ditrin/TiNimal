package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilterDTO(
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "breed") val breed: String?,
    @field:Json(name = "age") val age: Int?,
    @field:Json(name = "sex") val sex: Boolean?,
    @field:Json(name = "passport") val passport: Boolean?,
    @field:Json(name = "vaccination") val vaccination: Boolean?,
    @field:Json(name = "certificates") val certificates: Boolean?,
    @field:Json(name = "priceFrom") val priceFrom: Int?,
    @field:Json(name = "priceTo") val priceTo: Int?
)