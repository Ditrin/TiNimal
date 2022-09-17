package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass
import java.util.*
@JsonClass(generateAdapter = true)

data class FeedbackDTO(
        val id: Long,
        val userId: String,
        val date: Date,
        val text: String
)
