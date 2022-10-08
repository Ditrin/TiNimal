package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.domain.models.CatShort
import java.io.File

interface TiNimalRepository {

    suspend fun getUserById(id: Long): UserDTO
    suspend fun getCatsByUser(): List<CatDTO>
    suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long): CatDTO?
    suspend fun getFeedback(id: Long): List<FeedbackDTO>
    suspend fun getCatById(id: Long): CatDTO
}