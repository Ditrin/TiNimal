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

    private val tokenLiveData = MutableLiveData<String>("")
    val token: LiveData<String> = tokenLiveData

    private var job: Job? = null

    fun postAuthorization(authDTO: AuthDTO) {
        Log.d("qwerty", "ttt")
        job?.cancel()
        job = viewModelScope.launch {
            runCatching {
                useCase.execute(authDTO)
            }.onSuccess {
                tokenLiveData.value = it.jwttoken
                messageLiveData.value = "Success"
                Log.d("asd", it.jwttoken)
            }.onFailure {
                tokenLiveData.value = ""
                messageLiveData.value = "Неверные данные"
                Log.d("asd", it.toString())
            }
        }
    }
}