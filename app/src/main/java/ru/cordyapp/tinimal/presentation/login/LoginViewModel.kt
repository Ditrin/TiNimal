package ru.cordyapp.tinimal.presentation.login

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.AuthorizationUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthorizationUseCase) : ViewModel() {
    private val messageLiveData = MutableLiveData<String>("")
    val message: LiveData<String> = messageLiveData

    private val isEnabledLiveData = MutableLiveData<Boolean>()
    val isEnabled: LiveData<Boolean> = isEnabledLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    var token: String = ""
    var id : Long? = null


    fun postAuthorization(authDTO: AuthDTO) {
        Log.d("qwerty", "ttt")

        viewModelScope.launch {
            isEnabledLiveData.value = false
            runCatching {
                useCase.execute(authDTO)
            }.onSuccess {
                token = it.jwttoken
                id = it.id
                isSuccessLiveData.value = true
                messageLiveData.value = "Success"
                isEnabledLiveData.value = true
            }.onFailure {
                token = ""
                messageLiveData.value = "Неверные данные"
                isEnabledLiveData.value = true
            }
        }
    }
    fun setSuccess(value: Boolean){
        isSuccessLiveData.value = value
    }
}