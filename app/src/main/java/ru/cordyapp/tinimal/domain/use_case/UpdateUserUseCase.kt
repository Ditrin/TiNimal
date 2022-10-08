package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class UpdateUserUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(name: String, id: Long, file: File): UserDTO? {
        return repository.updateUser(name, id, file)
    }
}