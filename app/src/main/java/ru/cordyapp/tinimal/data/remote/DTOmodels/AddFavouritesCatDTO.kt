package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddFavouritesCatDTO(
    @field:Json(name = "id") val id: Long
)