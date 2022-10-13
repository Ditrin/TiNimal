package ru.cordyapp.tinimal.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.utils.Cat

interface TinimalApi {
    @GET("cats")
    suspend fun getAllCats(): List<CatDTO>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): UserDTO

    //    @Multipart
    @POST("users/{id}/cats")
    suspend fun addCat(
        @Body catAddDTO: CatAddDTO,
        @Path("id") id: Long,
//        @Part image: MultipartBody.Part
    ): CatDTO?

    @GET("users/{id}/feedbacks")
    suspend fun getFeedback(@Path("id") id: Long): List<FeedbackDTO>

    @GET("cats/{id}")
    suspend fun getCatById(@Path("id") id: Long): CatInfoDTO

    @POST("cat/{id}/avatar")
    suspend fun postCatPhotoById(
        @Path("id") id: Int,
    ): CatAvatarDTO

    @PUT("users/{id}")
    suspend fun updateUser(
        @Body userEditDTO: UserEditDTO,
        @Path("id") id: Long
    ): UserDTO

    @PUT("users/{id}/cats/{cat_id}")
    suspend fun updateCat(
        @Body catAddDTO: CatAddDTO,
        @Path("id") id: Long,
        @Path("cat_id") cat_id: Long
    ) : UserDTO

    @Multipart
    @POST("users/{id}/avatar")
    suspend fun postAvatar(
        @Path("id") id: Long,
        @Part file: MultipartBody.Part
    )

    @Multipart
    @POST("cats/{id}/photo")
    suspend fun postCatAvatar(
        @Path("id") id: Long,
        @Part file: MultipartBody.Part
    )

    @GET("/users/{id}/favourites")
    suspend fun getFavorites(
        @Path("id") id: Long
    ): List<CatDTO>

    @DELETE("/users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long
    )

    @POST("/users/{id}/feedbacks/")
    suspend fun postFeedback(
        @Path("id") id: Long,
        @Body feedbackNewDTO: FeedbackNewDTO
    ): FeedbackDTO
}