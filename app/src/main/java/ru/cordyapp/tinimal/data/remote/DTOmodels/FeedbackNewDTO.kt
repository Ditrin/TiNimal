package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedbackNewDTO(
    @field:Json(name = "userId") val userId: Long,
    @field:Json(name = "date") val date: String?,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "rating") val rating: Int
)
