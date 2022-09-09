package ru.cordyapp.tinimal.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO

interface TinimalApi {

    @POST("authenticate")
    suspend fun postAuthenticate(@Body authDTO: AuthDTO): String
}