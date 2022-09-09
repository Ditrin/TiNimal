package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository

class AuthorizationUseCase(private val repository: AuthorizationRepository) {
    suspend fun execute(authDTO: AuthDTO): String {
        return repository.postAuthenticate(authDTO)
    }
}