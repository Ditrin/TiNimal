package ru.cordyapp.tinimal.presentation.cat_profile.our_cat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatInfoDTO
import ru.cordyapp.tinimal.domain.use_case.GetCatByIdUseCase
import javax.inject.Inject

@HiltViewModel
class OurCatProfileViewModel @Inject constructor(private val getCatByIdUseCase: GetCatByIdUseCase) :
    ViewModel() {

    private val catLiveData = MutableLiveData<CatInfoDTO>()
    val cat: LiveData<CatInfoDTO> = catLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    fun getCat(id: Long) {
        viewModelScope.launch {
            runCatching {
                getCatByIdUseCase.execute(id)
            }.onSuccess {
                catLiveData.value = it
                isSuccessLiveData.value = true
            }.onFailure {
                isSuccessLiveData.value = false
            }
        }
    }
}