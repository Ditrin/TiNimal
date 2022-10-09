package ru.cordyapp.tinimal.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatterBuilder

@RequiresApi(Build.VERSION_CODES.O)
fun dateParse(s: String): String{
    val formatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter()
    formatter.parse(s)
    return s
}