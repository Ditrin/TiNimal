package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.CatShort
import java.io.File

interface TiNimalRepository {

    suspend fun getUserById(id: Long): UserDTO
    suspend fun getCatsByUser(): List<CatDTO>
    suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long, file: File): CatDTO?
}