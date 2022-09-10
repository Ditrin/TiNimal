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
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.AuthorizationUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthorizationUseCase) : ViewModel() {
    private val messageLiveData = MutableLiveData<String>("")
    val message: LiveData<String> = messageLiveData

    private var job: Job? = null
    var token: String? = null

    fun postAuthorization(authDTO: AuthDTO) {
        Log.d("qwerty", "ttt")
        job?.cancel()
        job = viewModelScope.launch {
            runCatching {
                useCase.execute(authDTO)
            }.onSuccess {
                Log.d("asd","111")
                token = it
            }.onFailure {
//                if (it is NetworkErrorException)
                    messageLiveData.postValue("Неверные данные")
                Log.d("asd",it.toString())
            }
            Log.d("asd","333")
        }
    }
}