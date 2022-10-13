package ru.cordyapp.tinimal.presentation.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.FilterDTO
import ru.cordyapp.tinimal.domain.use_case.PostFilterUseCase
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val postFilterUseCase: PostFilterUseCase) :
    ViewModel() {

    private val listCatLiveData = MutableLiveData<List<CatDTO>>()
    val listCat: LiveData<List<CatDTO>> = listCatLiveData

    private val isPassportLiveData = MutableLiveData<Boolean>(false)
    val isPassport: LiveData<Boolean> = isPassportLiveData

    private val isVaccinationLiveData = MutableLiveData<Boolean>(false)
    val isVaccination: LiveData<Boolean> = isVaccinationLiveData

    private val isCertificateLiveData = MutableLiveData<Boolean>(false)
    val isCertificate: LiveData<Boolean> = isCertificateLiveData

    private val isMaleLiveData = MutableLiveData<Boolean>(true)
    val isMale: LiveData<Boolean> = isMaleLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>(true)
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    fun postFilter(filterDTO: FilterDTO) {
        viewModelScope.launch {
            runCatching {
                postFilterUseCase.execute(filterDTO)
            }.onSuccess {
                listCatLiveData.postValue(it)
            }.onFailure {

            }
        }
    }

    fun setPassport() {
        isPassportLiveData.value = !isPassportLiveData.value!!
    }

    fun setVaccination() {
        isVaccinationLiveData.value = !isVaccinationLiveData.value!!
    }

    fun setCertificate() {
        isCertificateLiveData.value = !isCertificateLiveData.value!!
    }

    fun setSex(b: Boolean) {
        isMaleLiveData.value = b
    }
}