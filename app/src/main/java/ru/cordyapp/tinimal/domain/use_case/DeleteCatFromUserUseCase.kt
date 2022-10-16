package ru.cordyapp.tinimal.domain.use_case

import ru.cordyapp.tinimal.domain.repository.TiNimalRepository

class DeleteCatFromUserUseCase(private val repository: TiNimalRepository) {
    suspend fun execute(id: Long, id_cat: Long) {
        return repository.deleteCatFromUser(id, id_cat)
    }
}