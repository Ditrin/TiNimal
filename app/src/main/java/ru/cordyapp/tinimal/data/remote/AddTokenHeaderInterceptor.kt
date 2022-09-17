package ru.cordyapp.tinimal.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import ru.cordyapp.tinimal.utils.SharedPref

class AddTokenHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPref.authToken
        val originalRequest = chain.request()

        val request = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}