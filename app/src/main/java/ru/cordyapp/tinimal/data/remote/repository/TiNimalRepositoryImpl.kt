package ru.cordyapp.tinimal.data.remote.repository

import retrofit2.http.Path
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class TiNimalRepositoryImpl(private val api: TinimalApi): TiNimalRepository {


    override suspend fun getCatsByUser(): List<CatDTO> {
        return api.getAllCats()
    }

    override suspend fun getUserById(id: Long): UserDTO {
        return api.getUserById(id)
    }
}