package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository

class CreateUserUseCase(private val repository: AuthorizationRepository) {
    suspend fun execute(userAuthDTO: UserAuthDTO): String{
        return repository.postCreateUser(userAuthDTO)
    }
}