package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class GetFeedbackUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long): List<FeedbackDTO>{
        return repository.getFeedback(id)
    }
}