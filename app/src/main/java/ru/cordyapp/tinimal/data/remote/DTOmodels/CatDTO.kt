package ru.cordyapp.tinimal.data.remote.DTOmodels

data class CatDTO (
    val name: String,
    val sex: Boolean,
    val breed: String,
    val age: Int,
    val price: Int,
    val passport: Boolean,
    val vaccination: Boolean,
    val certificates: Boolean,
    val info: String,
    val photo: String?
)