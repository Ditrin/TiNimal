package ru.cordyapp.tinimal.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.domain.use_case.CreateUserUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val useCase: CreateUserUseCase) : ViewModel() {

    private val isValidateLiveData = MutableLiveData<Boolean>(false)
    val isValidate: LiveData<Boolean> = isValidateLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    var token = ""
    var id: Long? = null

    private var job: Job? = null

    fun createUser(userAuthDTO: UserAuthDTO) {
        job?.cancel()
        job = viewModelScope.launch {
            runCatching {
                useCase.execute(userAuthDTO)
            }.onSuccess {
                token = it.jwttoken
                id = it.id
                isSuccessLiveData.value = true
            }.onFailure {
                isSuccessLiveData.value = false
            }
        }
    }

    fun verify(
        login: String?,
        password: String?,
        name: String?,
        phoneNumber: String?,
        mail: String?,
        address: String?
    ) {
        if (login == "" || password == "" || name == "" || phoneNumber == "" || mail == "" || address == "")
            isValidateLiveData.postValue(false)
        else
            isValidateLiveData.postValue(true)

    }

}