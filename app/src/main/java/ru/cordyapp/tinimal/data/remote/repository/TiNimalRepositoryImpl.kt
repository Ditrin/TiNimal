package ru.cordyapp.tinimal.data.remote.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class TiNimalRepositoryImpl(private val api: TinimalApi): TiNimalRepository {
    override suspend fun getCatsList(cat: String): CatsDTO {
    return api.getAllCats()   }
}