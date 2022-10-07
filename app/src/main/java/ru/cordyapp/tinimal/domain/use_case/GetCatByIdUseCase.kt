package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class GetCatByIdUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long): CatDTO{
        return repository.getCatById(id)
    }
}