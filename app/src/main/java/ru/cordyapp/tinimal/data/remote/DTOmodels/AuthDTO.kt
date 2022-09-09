package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class AuthDTO(
    @field:Json(name = "username") val username: String,
    @field:Json(name = "password") val password: String
)
