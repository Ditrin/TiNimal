package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class UpdateUserUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(userEditDTO: UserEditDTO, id: Long): UserDTO {
        return repository.updateUser(userEditDTO, id)
    }
}