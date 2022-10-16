package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class CatsDTO(
    val cats: List<CatDTO>
)