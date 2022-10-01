package ru.cordyapp.tinimal.data.remote.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Path
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import java.io.File
import java.io.IOException

class TiNimalRepositoryImpl(private val api: TinimalApi): TiNimalRepository {


    override suspend fun getCatsByUser(): List<CatDTO> {
        return api.getAllCats()
    }

    override suspend fun addCatToUser(catAddDTO: CatAddDTO, id: Long, file: File): CatDTO? {
        return try {
            api.addCat(catAddDTO,id, image = MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody()
            ))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getUserById(id: Long): UserDTO {
        return api.getUserById(id)
    }
}