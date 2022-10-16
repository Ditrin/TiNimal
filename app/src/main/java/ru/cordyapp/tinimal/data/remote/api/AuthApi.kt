package ru.cordyapp.tinimal.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.TokenDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO

interface AuthApi {

    @POST("authenticate")
    suspend fun postAuthenticate(@Body authDTO: AuthDTO): TokenDTO

    @POST("signUp")
    suspend fun postCreateUser(@Body userAuthDTO: UserAuthDTO) : TokenDTO
}