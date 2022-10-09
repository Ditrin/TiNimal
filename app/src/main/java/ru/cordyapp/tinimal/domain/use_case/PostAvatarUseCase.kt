package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File

class PostAvatarUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, file: File) {
        return repository.postAvatar(id, file)
    }
}