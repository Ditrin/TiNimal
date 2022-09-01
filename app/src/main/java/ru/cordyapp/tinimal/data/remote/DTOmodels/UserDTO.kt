package ru.cordyapp.tinimal.data.remote.DTOmodels

data class UserDTO (
    val login: String,
    val password: String,
    val name: String,
    val phoneNumber: Long,
    val mail: String,
    val address: String,
    val avatar: String?,
    val ranking: Float?
)
