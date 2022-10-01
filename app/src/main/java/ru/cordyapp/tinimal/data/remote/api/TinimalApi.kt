package ru.cordyapp.tinimal.data.remote.api

import okhttp3.MultipartBody
import retrofit2.http.*
import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.domain.models.CatShort

interface TinimalApi {
    @GET("cats")
    suspend fun getAllCats() : List<CatDTO>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): UserDTO

    @Multipart
    @POST("users/{id}/cats")
    suspend fun addCat(@Body catAddDTO: CatAddDTO, @Path("id") id: Long, @Part image: MultipartBody.Part): CatDTO?




 //   @GET("")
}