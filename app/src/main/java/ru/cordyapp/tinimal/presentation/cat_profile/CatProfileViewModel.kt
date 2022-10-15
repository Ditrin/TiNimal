package ru.cordyapp.tinimal.presentation.cat_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.AddFavouritesCatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatInfoDTO
import ru.cordyapp.tinimal.domain.use_case.AddFavouritesCatUseCase
import ru.cordyapp.tinimal.domain.use_case.DeleteFavouritesCatUseCase
import ru.cordyapp.tinimal.domain.use_case.GetCatByIdUseCase
import javax.inject.Inject

@HiltViewModel
class CatProfileViewModel @Inject constructor(
    private val getCatByIdUseCase: GetCatByIdUseCase,
    private val addFavouritesCatUseCase: AddFavouritesCatUseCase,
    private val deleteFavouritesCatUseCase: DeleteFavouritesCatUseCase
) :
    ViewModel() {

    private val catLiveData = MutableLiveData<CatInfoDTO>()
    val cat: LiveData<CatInfoDTO> = catLiveData

    private val isLikedLiveData = MutableLiveData<Boolean>()
    val isLiked: LiveData<Boolean> = isLikedLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    private val isAddedLiveData = MutableLiveData<Boolean>()
    val isAdded: LiveData<Boolean> = isAddedLiveData

    private val isDeletedLiveData = MutableLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> = isDeletedLiveData

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

    fun addFavouritesCat(id: Long, addFavouritesCatDTO: AddFavouritesCatDTO) {
        viewModelScope.launch {
            runCatching {
                addFavouritesCatUseCase.execute(id, addFavouritesCatDTO)
            }.onSuccess {
                isAddedLiveData.value = true
            }.onFailure {
                isAddedLiveData.value = false
            }
        }
    }

    fun deleteFavouritesCat(id: Long, id_cat: Long) {
        viewModelScope.launch {
            runCatching {
                deleteFavouritesCatUseCase.execute(id, id_cat)
            }.onSuccess {
                isAddedLiveData.value = true
            }.onFailure {
                isAddedLiveData.value = false
            }
        }
    }

    fun setIsLiked(value: Boolean) {
        isLikedLiveData.value = value
    }
}