package ru.cordyapp.tinimal.data.remote.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Path
import ru.cordyapp.tinimal.data.remote.DTOmodels.*
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File
import java.io.IOException

class TiNimalRepositoryImpl(private val api: TinimalApi) : TiNimalRepository {


    override suspend fun getCatsByUser(): List<CatDTO> {
        return api.getAllCats()
    }

    override suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long): CatDTO? {
        return api.addCat(catAddDTO, id)
//        return try {
//            api.addCat(catAddDTO,id, image = MultipartBody.Part.createFormData(
//                "image",
//                file.name,
//                file.asRequestBody()
//            ))
//        } catch (e: IOException) {
//            e.printStackTrace()
//            null
//        }
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

    override suspend fun postAvatar(id: Long, file: File) {
        return try {
            api.postAvatar(id, image = MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody("image/*".toMediaType())
            ))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteUser(id: Long){
        return api.deleteUser(id)
    }

    override suspend fun getFavorites(id: Long): List<CatDTO> {
        return api.getFavorites(id)
    }

//    override suspend fun updateUser(name: String, id: Long, file: File): UserDTO? {
//        return try {
//            api.updateUser(
//                name, id, image = MultipartBody.Part.createFormData(
//                    "image",
//                    file.name,
//                    file.asRequestBody()
//                )
//            )
//        } catch (e: IOException) {
//            e.printStackTrace()
//            null
//        }
//    }


    override suspend fun getUserById(id: Long): UserDTO {
        return api.getUserById(id)
    }
}