package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.use_case.GetFeedbackUseCase
import ru.cordyapp.tinimal.domain.use_case.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class FeedbackProfileViewModel @Inject constructor(
    private val feedbackUseCase: GetFeedbackUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val feedbackListLiveData = MutableLiveData<List<FeedbackDTO>>()
    val feedbackList: LiveData<List<FeedbackDTO>> = feedbackListLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    fun getFeedbacks(id: Long) {
        viewModelScope.launch {
            runCatching {
                feedbackUseCase.execute(id)
            }.onSuccess {
                isSuccessLiveData.value = true
                feedbackListLiveData.postValue(it)
            }.onFailure {
                isSuccessLiveData.value = false
            }
        }
    }
}