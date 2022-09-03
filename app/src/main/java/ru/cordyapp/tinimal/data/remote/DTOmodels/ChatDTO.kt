package ru.cordyapp.tinimal.data.remote.DTOmodels

data class ChatDTO(
        val user: UserDTO,
        val messages: List<MessageDto>
)