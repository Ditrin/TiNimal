package ru.cordyapp.tinimal.domain.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.domain.models.FeedbackShort
import ru.cordyapp.tinimal.utils.DateForm

class FeedbackMapper : Mapper<FeedbackDTO, FeedbackShort>{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(t: FeedbackDTO): FeedbackShort {
        return FeedbackShort(
            t.avatar,
            t.name,
//            DateForm.dateParse(t.date!!.substring(0, 19).replace("T", " ")),
            DateForm.dateParse(System.currentTimeMillis()),
            t.text,
            (t.rating!! * 100) / 100.0.toFloat()
        )
    }

}