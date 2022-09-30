package ru.cordyapp.tinimal.domain.models

data class CatShort(
    val id: Long,
    val name: String,
    val sex: Boolean,
    val breed: String,
    val price: Int,
    val photo: String?,
    val address: String?
)
