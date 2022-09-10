package ru.cordyapp.tinimal.data.remote.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(private val api: TinimalApi): AuthorizationRepository{
    override suspend fun postAuthenticate(authDTO: AuthDTO): String {
        return api.postAuthenticate(authDTO)
    }

    override suspend fun postCreateUser(userAuthDTO: UserAuthDTO): String {
        return api.postCreateUser(userAuthDTO)
    }
}