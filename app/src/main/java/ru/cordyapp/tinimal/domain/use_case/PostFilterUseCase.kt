package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.FilterDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class PostFilterUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(filterDTO: FilterDTO): List<CatDTO> {
        return repository.postFilter(filterDTO)
    }
}