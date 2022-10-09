package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackNewDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class PostFeedbackUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, feedbackNewDTO: FeedbackNewDTO): FeedbackDTO{
        return repository.postFeedback(id, feedbackNewDTO)
    }
}