package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.google.gson.annotations.SerializedName

data class AuthDTO(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)
