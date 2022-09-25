package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.CatShort

interface TiNimalRepository {

//    suspend fun getUser(): List<UserDTO>
    suspend fun getCatsByUser(): List<CatDTO>
}