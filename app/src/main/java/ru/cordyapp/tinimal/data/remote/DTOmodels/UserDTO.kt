package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class UserDTO(
    val id: Long,
    val login: String,
    val password: String,
    val name: String?,
    val phoneNumber: Long?,
    val mail: String?,
    val address: String?,
    val avatar: String?,
    val ranking: Float?,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
    val feedbacks: List<FeedbackDTO>?,
    val cats: List<CatDTO>?,
    val favorites: List<CatDTO>?,
    val username: String,
    val authorities: Any?
) : Serializable