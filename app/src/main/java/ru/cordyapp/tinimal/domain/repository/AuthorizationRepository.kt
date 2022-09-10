package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO

interface AuthorizationRepository {
    suspend fun postAuthenticate(authDTO: AuthDTO): String

    suspend fun postCreateUser(userAuthDTO: UserAuthDTO): String
}