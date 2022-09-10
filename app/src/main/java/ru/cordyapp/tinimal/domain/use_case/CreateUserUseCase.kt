package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.TokenDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository

class CreateUserUseCase(private val repository: AuthorizationRepository) {
    suspend fun execute(userAuthDTO: UserAuthDTO): TokenDTO{
        return repository.postCreateUser(userAuthDTO)
    }
}