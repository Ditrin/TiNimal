package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class GeUserUseCase (
    private val repository: TiNimalRepository
        ){
    suspend fun execute(): List<UserDTO>{
        return repository.getUser()
    }
}