package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass
import java.util.*
@JsonClass(generateAdapter = true)

data class MessageDto(
        val date: Date,
        val message: String
)
