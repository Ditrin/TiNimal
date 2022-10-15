package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.FilterDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.SearchDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class PostSearchUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(searchDTO: SearchDTO): List<CatDTO> {
        return repository.postSearch(searchDTO)
    }
}