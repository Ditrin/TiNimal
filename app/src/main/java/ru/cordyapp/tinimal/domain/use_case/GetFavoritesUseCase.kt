package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class GetFavoritesUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long): List<CatDTO> {
        return repository.getFavorites(id)
    }
}