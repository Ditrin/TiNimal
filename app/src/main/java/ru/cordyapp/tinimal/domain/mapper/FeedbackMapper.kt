package ru.cordyapp.tinimal.domain.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.FeedbackShort
import ru.cordyapp.tinimal.utils.dateParse

class FeedbackMapper : Mapper<FeedbackDTO, FeedbackShort>{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(t: FeedbackDTO): FeedbackShort {
        return FeedbackShort(
            "https://cordy-app.herokuapp.com/avatars/" + t.id.toString(),
            t.name,
            dateParse(t.date!!.substring(0, 19).replace("T", " ")),
            t.text,
            t.rating
        )
    }

}