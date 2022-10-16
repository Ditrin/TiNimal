package ru.cordyapp.tinimal.presentation.profile_edit

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.domain.use_case.DeleteUserUseCase
import ru.cordyapp.tinimal.domain.use_case.PostAvatarUseCase
import ru.cordyapp.tinimal.domain.use_case.UpdateUserUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val postAvatarUseCase: PostAvatarUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val userLiveData = MutableLiveData<UserDTO>()
    val user: LiveData<UserDTO> = userLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private val isUpdateSuccessLiveData = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> = isUpdateSuccessLiveData

    private val isEnabledLiveData = MutableLiveData<Boolean>(true)
    val isEnabled: LiveData<Boolean> = isEnabledLiveData

    private val isAvatarSuccessLiveData = MutableLiveData<Boolean>()
    val isAvatar: LiveData<Boolean> = isAvatarSuccessLiveData

    private val pathImageLiveData = MutableLiveData<Uri>()
    val pathImage: LiveData<Uri> = pathImageLiveData

    fun update(userEditDTO: UserEditDTO, id: Long) {
        viewModelScope.launch {
            runCatching {
                isEnabledLiveData.value = false
                updateUserUseCase.execute(userEditDTO, id)
            }.onSuccess {
                isEnabledLiveData.value = true
                isUpdateSuccessLiveData.value = true
                userLiveData.postValue(it)
            }.onFailure {
                isEnabledLiveData.value = true
                Log.d("EDIT_TAG", it.toString())
                isSuccessLiveData.value = false
            }
        }
    }

    fun postAvatar(id: Long, file: MultipartBody.Part) {
        viewModelScope.launch {
            runCatching {
                isEnabledLiveData.value = false
                postAvatarUseCase.execute(id, file)
            }.onSuccess {
                isEnabledLiveData.value = true
                isAvatarSuccessLiveData.value = true
                Log.d("TAG_AVATAR", "all good")
            }.onFailure {
                isEnabledLiveData.value = true
                isAvatarSuccessLiveData.value = false
                Log.d("TAG_AVATAR", it.toString())
            }
        }
    }



    fun saveImagePath(path: Uri){
        pathImageLiveData.postValue(path)
    }

    fun deleteUser(id: Long){
        viewModelScope.launch {
            runCatching {
               deleteUserUseCase.execute(id)
    }.onSuccess {
            Log.d("TAG_AVATAR", "all good")
        }.onFailure {
            Log.d("TAG_AVATAR", it.toString())
        }
    }
}

}