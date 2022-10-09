package ru.cordyapp.tinimal.presentation.profile_edit

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.domain.use_case.UpdateUserUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val userLiveData = MutableLiveData<UserDTO>()
    val user: LiveData<UserDTO> = userLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private val pathImageLiveData = MutableLiveData<Uri>()
    val pathImage: LiveData<Uri> = pathImageLiveData

    fun update(userEditDTO: UserEditDTO, id: Long) {
        viewModelScope.launch {
            runCatching {
                updateUserUseCase.execute(userEditDTO, id)
            }.onSuccess {
                isSuccessLiveData.value = true
                userLiveData.postValue(it)
            }.onFailure {
                Log.d("EDIT_TAG", it.toString())
                isSuccessLiveData.value = false
            }
        }
    }

    fun saveImagePath(path: Uri){
        pathImageLiveData.postValue(path)
    }

}