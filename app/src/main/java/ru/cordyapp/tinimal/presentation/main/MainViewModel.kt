package ru.cordyapp.tinimal.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.domain.use_case.GetCatsListByUserUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCatsList: GetCatsListByUserUseCase
) : ViewModel() {

//    private val isLoadLiveData = MutableLiveData(false)
//
//    val isLoad: LiveData<Boolean>
//        get() = isLoadLiveData

    private val catsListLiveData = MutableLiveData<List<CatDTO>>()

    val catsList: LiveData<List<CatDTO>>
        get() = catsListLiveData

    private var searchJob: Job? = null


    fun getUsersListByLogin() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            runCatching {
                getCatsList.execute()
            }.onSuccess {

                catsListLiveData.postValue(it)
            }.onFailure {
                Log.d("tags", "error $it" )
                catsListLiveData.postValue(emptyList())
            }
        }
    }
}