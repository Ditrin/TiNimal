package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.AddFavouritesCatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class DeleteFavouritesCatUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, id_cat: Long) {
        repository.deleteFavoutiresCat(id, id_cat)
    }
}