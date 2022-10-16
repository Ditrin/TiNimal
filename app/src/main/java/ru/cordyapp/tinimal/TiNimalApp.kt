package ru.cordyapp.tinimal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.cordyapp.tinimal.utils.SharedPref

@HiltAndroidApp
class TiNimalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}