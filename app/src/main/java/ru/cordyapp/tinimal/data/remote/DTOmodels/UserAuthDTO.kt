package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAuthDTO(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "password") val password: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "phoneNumber") val phoneNumber: Long,
    @field:Json(name = "mail") val mail: String,
    @field:Json(name = "address") val address: String,
)