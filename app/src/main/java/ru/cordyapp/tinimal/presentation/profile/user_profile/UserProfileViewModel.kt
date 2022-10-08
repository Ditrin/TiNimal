package ru.cordyapp.tinimal.presentation.profile.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getCatsList: GetUserUseCase
) : ViewModel() {

    private val catsListLiveData = MutableLiveData<UserDTO>()
    val catsList: LiveData<UserDTO>
        get() = catsListLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private var searchJob: Job? = null

    fun getUsersListByLogin(id: Long) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            runCatching {
                getCatsList.execute(id)
            }.onSuccess {
                catsListLiveData.postValue(it)
                isSuccessLiveData.postValue(true)
            }.onFailure {
                isSuccessLiveData.postValue(false)
            }
        }
    }
}