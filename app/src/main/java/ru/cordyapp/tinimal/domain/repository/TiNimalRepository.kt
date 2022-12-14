package ru.cordyapp.tinimal.domain.repository

import okhttp3.MultipartBody
import retrofit2.http.Part
import ru.cordyapp.tinimal.data.remote.DTOmodels.*

interface TiNimalRepository {

    suspend fun getUserById(id: Long): UserDTO
    suspend fun getCatsByUser(): List<CatDTO>
    suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long): CatDTO?
    suspend fun getFeedback(id: Long): List<FeedbackDTO>
    suspend fun getCatById(id: Long): CatInfoDTO
    suspend fun updateUser(userEditDTO: UserEditDTO, id: Long): UserDTO
    suspend fun updateCat(catAddDTO: CatAddDTO, id: Long, id_cat: Long): UserDTO
    suspend fun postAvatar(id: Long, @Part file: MultipartBody.Part)
    suspend fun getFavorites(id: Long): List<CatDTO>
    suspend fun deleteUser(id: Long)
    suspend fun postFeedback(id: Long, feedbackNewDTO: FeedbackNewDTO): FeedbackDTO
    suspend fun postCatAvatar(id: Long, @Part file: MultipartBody.Part)
    suspend fun postFilter(filterDTO: FilterDTO): List<CatDTO>
    suspend fun addFavouritesCat(id: Long, addFavouritesCatDTO: AddFavouritesCatDTO): CatDTO
    suspend fun deleteFavoutiresCat(id: Long, id_cat: Long)
    suspend fun deleteCatFromUser(id: Long, id_cat: Long)
    suspend fun postSearch(searchDTO: SearchDTO): List<CatDTO>
}