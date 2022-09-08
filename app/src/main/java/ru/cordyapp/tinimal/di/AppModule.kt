package ru.cordyapp.tinimal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTinimalApi() : TinimalApi {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TinimalApi::class.java)
    }
}