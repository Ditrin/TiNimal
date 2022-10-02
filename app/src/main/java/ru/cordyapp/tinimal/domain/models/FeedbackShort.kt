package ru.cordyapp.tinimal.domain.models

data class FeedbackShort(
    val photo: String?,
    val name: String?,
    val date: String?,
    val info: String,
    val rating: Float?
)