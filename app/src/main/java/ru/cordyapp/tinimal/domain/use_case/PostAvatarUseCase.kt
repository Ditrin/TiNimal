package ru.cordyapp.tinimal.domain.use_case

import okhttp3.MultipartBody
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class PostAvatarUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, file: MultipartBody.Part) {
        return repository.postAvatar(id, file)
    }
}