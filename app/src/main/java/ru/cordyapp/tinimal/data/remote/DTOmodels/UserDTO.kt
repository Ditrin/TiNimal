package ru.cordyapp.tinimal.data.remote.DTOmodels

data class UserDTO (
    val id: Long,
    val login: String,
    val password: String,
    val name: String,
    val phoneNumber: Long,
    val mail: String,
    val address: String,
    val avatar: String?,
    val ranking: Float?,
    val feedbacks: List<FeedbackDTO>?,
    val cats: List<CatDTO>?,
    val favorites: List<CatDTO>?,
    val chats: List<ChatDTO>?
)
