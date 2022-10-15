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

    @POST("users/{id}/cats")
    suspend fun addCat(
        @Body catAddDTO: CatAddDTO,
        @Path("id") id: Long,
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
    ): UserDTO

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

    @POST("/cats/search")
    suspend fun postFilter(
        @Body filterDTO: FilterDTO
    ): List<CatDTO>

    @POST("/users/{id}/favourites")
    suspend fun addFavouritesCat(
        @Path("id") id: Long,
        @Body addFavouritesCatDTO: AddFavouritesCatDTO
    ): CatDTO

    @DELETE("/users/{id}/favourites/{id_cat}")
    suspend fun deleteFavouritesCat()

    @DELETE("/users/{id}/cats/{id_cat}")
    suspend fun deleteCatFromUser(
        @Path("id") id: Long,
        @Path("id_cat") id_cat: Long
    )

    @POST("/cats/filter")
    suspend fun postSearch(
        @Body searchDTO: SearchDTO
    ): List<CatDTO>
}