package ru.cordyapp.tinimal.presentation.login

import android.accounts.NetworkErrorException
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository
import ru.cordyapp.tinimal.domain.use_case.AuthorizationUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthorizationUseCase): ViewModel() {
    private val messageLiveData = MutableLiveData<String>()
    val message: LiveData<String> = messageLiveData

    private var job: Job? = null
    var token: String? = null
    fun postAuthorization(login: String, password: String){
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                useCase.execute(AuthDTO(login, password))
            }.onSuccess {

            }.onFailure {
                if(it is NetworkErrorException)
                    messageLiveData.postValue("Неверные данные")
            }

        }
    }
}