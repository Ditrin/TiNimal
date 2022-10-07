package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass
import java.util.*
@JsonClass(generateAdapter = true)

data class FeedbackDTO(
        val id: Long,
        val user_id: Int,
        val date: String?,
        val text: String,
        val name: String?,
        val rating: Float?
)
