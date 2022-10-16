package ru.cordyapp.tinimal.domain.models

import java.io.Serializable

data class CatShort(
    val id: Long,
    val name: String,
    val sex: Boolean,
    val breed: String,
    val price: Int,
    val photo: String?
) : Serializable
