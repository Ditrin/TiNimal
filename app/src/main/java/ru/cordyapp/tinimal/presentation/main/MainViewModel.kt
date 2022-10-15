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
import ru.cordyapp.tinimal.data.remote.DTOmodels.SearchDTO
import ru.cordyapp.tinimal.domain.use_case.GetCatsListByUserUseCase
import ru.cordyapp.tinimal.domain.use_case.PostSearchUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCatsList: GetCatsListByUserUseCase,
    private val postSearchUseCase: PostSearchUseCase
) : ViewModel() {

    private val catsListLiveData = MutableLiveData<List<CatDTO>>()

    val catsList: LiveData<List<CatDTO>>
        get() = catsListLiveData

    private val searchTextLiveData = MutableLiveData<String>()
    val searchText: LiveData<String> = searchTextLiveData

    private var searchJob: Job? = null


    fun getUsersListByLogin() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            runCatching {
                getCatsList.execute()
            }.onSuccess {

                catsListLiveData.postValue(it)
            }.onFailure {
                Log.d("tags", "error $it")
                catsListLiveData.postValue(emptyList())
            }
        }
    }

    fun postSearch(searchDTO: SearchDTO) {
        viewModelScope.launch {
            runCatching {
                postSearchUseCase.execute(searchDTO)
            }.onSuccess {
                catsListLiveData.postValue(it)
            }.onFailure {
                catsListLiveData.postValue(emptyList())
            }
        }
    }

    fun setCatsList(list: List<CatDTO>) {
        catsListLiveData.value = list
    }
}