package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class GetUserUseCase(
    private val repository: TiNimalRepository
) {
    suspend fun execute(): UserDTO {
        return repository.getUser()
    }
}