package ru.cordyapp.tinimal.domain.mapper

import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.FeedbackShort

class FeedbackMapper : DoubleMapper<FeedbackDTO, UserDTO, FeedbackShort> {
    override fun map(t: FeedbackDTO, p: UserDTO): FeedbackShort {
        return FeedbackShort(p.avatar, p.name, t.date, t.text, p.ranking)
    }
}