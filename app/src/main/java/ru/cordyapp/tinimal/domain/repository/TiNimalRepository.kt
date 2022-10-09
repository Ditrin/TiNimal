package ru.cordyapp.tinimal.domain.repository

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Part
import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.domain.models.CatShort
import java.io.File

interface TiNimalRepository {

    suspend fun getUserById(id: Long): UserDTO
    suspend fun getCatsByUser(): List<CatDTO>
    suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long): CatDTO?
    suspend fun getFeedback(id: Long): List<FeedbackDTO>
    suspend fun getCatById(id: Long): CatInfoDTO
    suspend fun updateUser(userEditDTO: UserEditDTO, id: Long): UserDTO
    suspend fun postAvatar(id: Long, @Part file: File)
    suspend fun getFavorites(id: Long): List<CatDTO>
    suspend fun deleteUser(id: Long)
    suspend fun postFeedback(id: Long, feedbackNewDTO: FeedbackNewDTO): FeedbackDTO
}