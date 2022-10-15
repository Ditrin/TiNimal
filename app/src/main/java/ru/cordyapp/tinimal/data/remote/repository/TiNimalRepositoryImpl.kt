package ru.cordyapp.tinimal.data.remote.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Part
import retrofit2.http.Path
import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import ru.cordyapp.tinimal.utils.User
import java.io.File
import java.io.IOException

class TiNimalRepositoryImpl(private val api: TinimalApi) : TiNimalRepository {


    override suspend fun getCatsByUser(): List<CatDTO> {
        return api.getAllCats()
    }

    override suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long): CatDTO? {
        return api.addCat(catAddDTO, id)
    }

    override suspend fun getFeedback(id: Long): List<FeedbackDTO> {
        return api.getFeedback(id)
    }

    override suspend fun getCatById(id: Long): CatInfoDTO {
        return api.getCatById(id)
    }

    override suspend fun updateUser(userEditDTO: UserEditDTO, id: Long): UserDTO {
        return api.updateUser(userEditDTO, id)
    }

    override suspend fun updateCat(catAddDTO: CatAddDTO, id: Long, id_cat: Long): UserDTO {
        return api.updateCat(catAddDTO, id, id_cat)
    }

    override suspend fun postAvatar(id: Long, @Part file: MultipartBody.Part) {
        return try {
            api.postAvatar(id, file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteUser(id: Long) {
        return api.deleteUser(id)
    }

    override suspend fun postFeedback(id: Long, feedbackNewDTO: FeedbackNewDTO): FeedbackDTO {
        return api.postFeedback(id, feedbackNewDTO)
    }

    override suspend fun postCatAvatar(id: Long, file: MultipartBody.Part) {
        return try {
            api.postCatAvatar(id, file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override suspend fun postFilter(filterDTO: FilterDTO): List<CatDTO> {
        return api.postFilter(filterDTO)
    }

    override suspend fun addFavouritesCat(
        id: Long,
        addFavouritesCatDTO: AddFavouritesCatDTO
    ): CatDTO {
        return api.addFavouritesCat(id, addFavouritesCatDTO)
    }

    override suspend fun deleteFavoutiresCat(id: Long, id_cat: Long) {
        api.deleteFavouritesCat()
    }

    override suspend fun deleteCatFromUser(id: Long, id_cat: Long) {
        return api.deleteCatFromUser(id, id_cat)
    }

    override suspend fun postSearch(searchDTO: SearchDTO): List<CatDTO> {
        return api.postSearch(searchDTO)
    }

    override suspend fun getFavorites(id: Long): List<CatDTO> {
        return api.getFavorites(id)
    }

    override suspend fun getUserById(id: Long): UserDTO {
        return api.getUserById(id)
    }
}