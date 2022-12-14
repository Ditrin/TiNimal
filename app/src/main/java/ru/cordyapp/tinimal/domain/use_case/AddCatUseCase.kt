package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAvatarDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class AddCatUseCase (private val repository: TiNimalRepository) {
    suspend fun execute(catAddDTO: CatAddDTO, id: Long): CatDTO? {
        return repository.addCatToUser(catAddDTO, id)
    }
}