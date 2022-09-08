package ru.cordyapp.tinimal.domain.repository

import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO

interface AuthorizationRepository {
    suspend fun postAuthenticate(authDTO: AuthDTO): String
}