package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.domain.models.CatShort

interface TiNimalRepository {

    suspend fun getCatsList(cat: String): List<CatDTO>
}