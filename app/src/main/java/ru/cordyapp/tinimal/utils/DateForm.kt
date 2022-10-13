package ru.cordyapp.tinimal.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
//fun dateParse(s: String): String{
//    val formatter = DateTimeFormatterBuilder()
//        .appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter()
//    formatter.parse(s)
//    return s

object DateForm {
    fun dateParse(millis: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(millis)
    }
}