package ru.cordyapp.tinimal.presentation.cat_form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.domain.use_case.AddCatUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CatFormViewModel @Inject constructor(private val addCatUseCase: AddCatUseCase) : ViewModel() {
    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private val isPassportLiveData = MutableLiveData<Boolean>(false)
    val isPassport: LiveData<Boolean> = isPassportLiveData

    private val isVaccinationLiveData = MutableLiveData<Boolean>(false)
    val isVaccination: LiveData<Boolean> = isVaccinationLiveData

    private val isCertificateLiveData = MutableLiveData<Boolean>(false)
    val isCertificate: LiveData<Boolean> = isCertificateLiveData

    private val isMaleLiveData = MutableLiveData<Boolean>(true)
    val isMale: LiveData<Boolean> = isMaleLiveData

    fun addCat(catAddDTO: CatAddDTO, id: Long, file: File) {
        viewModelScope.launch {
            runCatching {
                addCatUseCase.execute(catAddDTO, id, file)
            }
                .onSuccess {
                    isSuccessLiveData.value = true
                }
                .onFailure {
                    isSuccessLiveData.value = false
                }
        }
    }

    fun setPassport(){
        isPassportLiveData.value = !isPassportLiveData.value!!
    }

    fun setVaccination(){
        isVaccinationLiveData.value = !isVaccinationLiveData.value!!
    }

    fun setCertificate(){
        isCertificateLiveData.value = !isCertificateLiveData.value!!
    }

    fun setSex(b: Boolean){
        isMaleLiveData.value = b
    }

    fun verify(){

    }
}