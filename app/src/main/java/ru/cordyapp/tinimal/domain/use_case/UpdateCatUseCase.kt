package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatInfoDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class UpdateCatUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(catAddDTO: CatAddDTO, id: Long, id_cat: Long): UserDTO {
        return repository.updateCat(catAddDTO, id, id_cat)
    }
}