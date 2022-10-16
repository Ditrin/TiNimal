package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.TokenDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO

interface AuthorizationRepository {
    suspend fun postAuthenticate(authDTO: AuthDTO): TokenDTO

    suspend fun postCreateUser(userAuthDTO: UserAuthDTO): TokenDTO
}