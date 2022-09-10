package ru.cordyapp.tinimal.domain.Mapper

import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.domain.models.CatShort

class CatMapper : Mapper<CatDTO, CatShort>{
    override fun map(i: CatDTO): CatShort {
        return CatShort(
            id = i.id,
            name = i.name,
            sex = i.sex,
            breed = i.breed,
            price = i.price,
            photo = i.photo
        )
    }
}