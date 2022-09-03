package ru.cordyapp.tinimal.data.remote.DTOmodels

import java.util.*

data class FeedbackDTO(
        val id: Long,
        val userId: String,
        val date: Date,
        val text: String
)
