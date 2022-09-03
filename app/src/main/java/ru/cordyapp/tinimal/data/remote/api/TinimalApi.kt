package ru.cordyapp.tinimal.data.remote.api

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO

interface TinimalApi {

    @Headers("Content-Type: application/json")
    @POST("authenticate")
    fun postAuthenticate(@Body authDTO: AuthDTO): String
}