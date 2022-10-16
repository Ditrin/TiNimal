package ru.cordyapp.tinimal.domain.mapper

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.models.CatShort

class CatMapper : Mapper<CatDTO, CatShort> {
    override fun map(t: CatDTO): CatShort {
        return CatShort(
            id = t.id,
            name = t.name ?: "",
            sex = t.sex,
            breed = t.breed ?: "",
            price = t.price ?: 0,
            photo = t.photo
        )
    }
}