package ru.cordyapp.tinimal.presentation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.GetCatsListUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCatsList: GetCatsListUseCase
): ViewModel() {

    private val userLiveData = MutableLiveData<UserDTO>()

    val catsList: LiveData<UserDTO>
        get() = userLiveData

    private var searchJob: Job? = null


    fun getUsersListByLogin() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            runCatching {
                getCatsList.execute()
            }.onSuccess {

                userLiveData.postValue(it)
            }.onFailure {
                Log.d("tags", "error $it" )
                catsListLiveData.postValue(emptyList())
            }
        }
    }
}