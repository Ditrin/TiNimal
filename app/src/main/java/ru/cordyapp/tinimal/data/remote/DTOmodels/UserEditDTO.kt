package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEditDTO(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "phoneNumber") val phoneNumber: Long,
    @field:Json(name = "mail") val mail: String,
    @field:Json(name = "address") val address: String
)