package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.AddFavouritesCatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class AddFavouritesCatUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, addFavouritesCatDTO: AddFavouritesCatDTO): CatDTO {
        return repository.addFavouritesCat(id, addFavouritesCatDTO)
    }
}