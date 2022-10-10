package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class DeleteUserUseCase(private val repository: TiNimalRepository)
{
    suspend fun execute(id: Long) {
        return repository.deleteUser(id)
    }
}