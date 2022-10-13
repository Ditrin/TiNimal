package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackNewDTO
import ru.cordyapp.tinimal.domain.use_case.PostFeedbackUseCase
import javax.inject.Inject

@HiltViewModel
class FeedbackNewViewModel @Inject constructor(
    private val postFeedbackUseCase: PostFeedbackUseCase
) : ViewModel() {

    private val ratingLiveData = MutableLiveData<Int>()
    val rating: LiveData<Int> = ratingLiveData

    private val isSuccessLiveData = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessLiveData

    fun getFeedbacks(id: Long, feedbackNewDTO: FeedbackNewDTO) {
        viewModelScope.launch {
            runCatching {
                postFeedbackUseCase.execute(id, feedbackNewDTO)
            }.onSuccess {
                isSuccessLiveData.value = true
            }.onFailure {
                isSuccessLiveData.value = false
            }
        }
    }

    fun setRating(rating: Int) {
        ratingLiveData.postValue(rating)
    }
}