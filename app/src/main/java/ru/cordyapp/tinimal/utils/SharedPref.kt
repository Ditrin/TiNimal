package ru.cordyapp.tinimal.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    private var sharedPrefs: SharedPreferences? = null

    var authToken: String?
        get() = sharedPrefs?.getString(AUTH_TOKEN, null)
        set(value) {
            sharedPrefs?.edit()
                ?.putString(AUTH_TOKEN, value)
                ?.apply()
        }

    var id: Long?
        get() = sharedPrefs?.getLong(ID, -1)
        set(value) {
            if (value != null) {
                sharedPrefs?.edit()
                    ?.putLong(ID, value)
                    ?.apply()
            }
        }


    fun init(context: Context){
        sharedPrefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    }

    private const val NAME = "app_preferences"
    private const val AUTH_TOKEN = "auth_token"
    private const val ID = "id"
}