package ru.cordyapp.tinimal.data.remote.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.TokenDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.data.remote.api.AuthApi
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(private val api: AuthApi): AuthorizationRepository{
    override suspend fun postAuthenticate(authDTO: AuthDTO): TokenDTO {
        return api.postAuthenticate(authDTO)
    }

    override suspend fun postCreateUser(userAuthDTO: UserAuthDTO): TokenDTO {
        return api.postCreateUser(userAuthDTO)
    }
}