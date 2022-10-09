package ru.cordyapp.tinimal.domain.mapper

import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.FeedbackShort

class FeedbackMapper : Mapper<FeedbackDTO, FeedbackShort> {
    override fun map(t: FeedbackDTO): FeedbackShort {
        return FeedbackShort(
            t.avatar,
            t.name,
            t.date,
            t.text,
            t.rating
        )
    }

}