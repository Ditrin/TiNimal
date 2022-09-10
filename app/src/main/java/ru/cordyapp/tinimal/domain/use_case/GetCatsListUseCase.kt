package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class GetCatsListUseCase (
    private val repository: TiNimalRepository
        ){
    suspend fun execute(cats:String): CatsDTO{
        return repository.getCatsList(cats)
    }
}