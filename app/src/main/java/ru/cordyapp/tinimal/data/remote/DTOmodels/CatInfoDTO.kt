package ru.cordyapp.tinimal.data.remote.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatInfoDTO(
    val id: Long,
    val name: String,
    val sex: Boolean,
    val breed: String,
    val age: Int,
    val price: Int,
    val passport: Boolean,
    val vaccination: Boolean,
    val certificates: Boolean,
    val info: String,
    val photo: String?,
    val owner_id: Long,
    val owner_phoneNumber: Long,
    val owner_mail: String,
    val owner_address: String,
    val owner_ranking: Float?,
    val owner_name: String,
    val count_feedback: Int
)