package ru.cordyapp.tinimal.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.CatShort

interface TinimalApi {
    @GET("cats")
    suspend fun getAllCats() : List<CatDTO>

//    @GET("users/{id}/cats")
//    suspend fun getCatsByUser(
//        @Path("id") id: Int
//    ): List<CatDTO>




 //   @GET("")
}