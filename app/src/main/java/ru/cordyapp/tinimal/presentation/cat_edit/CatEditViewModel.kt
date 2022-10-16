package ru.cordyapp.tinimal.presentation.cat_edit

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.DeleteCatFromUserUseCase
import ru.cordyapp.tinimal.domain.use_case.PostCatAvatarUseCase
import ru.cordyapp.tinimal.domain.use_case.UpdateCatUseCase
import javax.inject.Inject

@HiltViewModel
class CatEditViewModel @Inject constructor(
    private val updateCatUseCase: UpdateCatUseCase,
    private val postCatAvatarUseCase: PostCatAvatarUseCase,
    private val deleteCatFromUserUseCase: DeleteCatFromUserUseCase
) : ViewModel() {

    private val isValidateLiveData = MutableLiveData<Boolean>(false)
    val isValidate: LiveData<Boolean> = isValidateLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private val isDeleteLiveData = MutableLiveData<Boolean>()
    val isDelete: LiveData<Boolean> = isDeleteLiveData

    private val isPostAvatarLiveData = MutableLiveData<Boolean>()
    val isPostAvatar: LiveData<Boolean> = isPostAvatarLiveData

    private val isPassportLiveData = MutableLiveData<Boolean>(false)
    val isPassport: LiveData<Boolean> = isPassportLiveData

    private val isVaccinationLiveData = MutableLiveData<Boolean>(false)
    val isVaccination: LiveData<Boolean> = isVaccinationLiveData

    private val isCertificateLiveData = MutableLiveData<Boolean>(false)
    val isCertificate: LiveData<Boolean> = isCertificateLiveData

    private val isMaleLiveData = MutableLiveData<Boolean>(true)
    val isMale: LiveData<Boolean> = isMaleLiveData

    private val pathImageLiveData = MutableLiveData<Uri>()
    val pathImage: LiveData<Uri> = pathImageLiveData

    private val userLiveData = MutableLiveData<UserDTO>()
    val user: LiveData<UserDTO> = userLiveData

    private val isEnabledLiveData = MutableLiveData<Boolean>(true)
    val isEnabled: LiveData<Boolean> = isEnabledLiveData

    fun update(catAddDTO: CatAddDTO, id: Long, id_cat: Long) {
        viewModelScope.launch {
            runCatching {
                isEnabledLiveData.value = false
                updateCatUseCase.execute(catAddDTO, id, id_cat)
            }
                .onSuccess {
                    isEnabledLiveData.value = true
                    userLiveData.value = it
                    isSuccessLiveData.value = true
                }
                .onFailure {
                    isEnabledLiveData.value = true
                    Log.d("Error_tag", it.toString())
                    isSuccessLiveData.value = false
                }
        }
    }

    fun postAvatar(id: Long, file: MultipartBody.Part) {
        viewModelScope.launch {
            runCatching {
                isEnabledLiveData.value = false
                postCatAvatarUseCase.execute(id, file)
            }.onSuccess {
                isEnabledLiveData.value = true
                isPostAvatarLiveData.value = true
            }.onFailure {
                isEnabledLiveData.value = true
                isPostAvatarLiveData.value = false
            }
        }
    }

    fun deleteCat(id: Long, id_cat: Long) {
        viewModelScope.launch {
            runCatching {
                deleteCatFromUserUseCase.execute(id, id_cat)
            }.onSuccess {
                isDeleteLiveData.value = true
            }.onFailure {
                isDeleteLiveData.value = false
            }
        }
    }

    fun setPassport() {
        isPassportLiveData.value = !isPassportLiveData.value!!
    }

    fun setVaccination() {
        isVaccinationLiveData.value = !isVaccinationLiveData.value!!
    }

    fun setCertificate() {
        isCertificateLiveData.value = !isCertificateLiveData.value!!
    }

    fun setSex(b: Boolean) {
        isMaleLiveData.value = b
    }

    fun saveImagePath(path: Uri) {
        pathImageLiveData.postValue(path)
    }

    fun verify(
        name: String?,
        breed: String?,
        age: String?,
        price: String?,
        catStory: String?
    ) {
        if (name == "" || breed == "" || age == "" || price == "" || catStory == "")
            isValidateLiveData.postValue(false)
        else
            isValidateLiveData.postValue(true)

    }
}