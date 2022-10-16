package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDTO(
    val jwttoken: String,
    val id: Long,
    val login: String,
    val name: String,
    val phoneNumber: Long,
    val mail: String,
    val address: String,
    val avatar: String?,
    val ranking: Float?,
    val feedbacks: List<FeedbackDTO>?,
    val cats: List<CatDTO>?,
    val favorites: List<CatDTO>?
)