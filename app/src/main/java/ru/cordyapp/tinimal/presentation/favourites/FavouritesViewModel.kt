package ru.cordyapp.tinimal.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.use_case.GetCatByIdUseCase
import ru.cordyapp.tinimal.domain.use_case.GetFavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val getFavoritesUseCase: GetFavoritesUseCase) :
    ViewModel() {

    private val catsListLiveData = MutableLiveData<List<CatDTO>>()
    val catsList: LiveData<List<CatDTO>> = catsListLiveData

    fun getCats(id: Long) {
        viewModelScope.launch {
            runCatching {
                getFavoritesUseCase.execute(id)
            }.onSuccess {
                catsListLiveData.value = it
            }.onFailure {

            }
        }
    }
}