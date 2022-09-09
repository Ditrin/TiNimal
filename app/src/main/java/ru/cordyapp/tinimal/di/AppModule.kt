package ru.cordyapp.tinimal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.data.remote.repository.AuthorizationRepositoryImpl
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository
import ru.cordyapp.tinimal.domain.use_case.AuthorizationUseCase
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

    @Provides
    @Singleton
    fun provideAuthorizationUseCase(repository: AuthorizationRepository): AuthorizationUseCase {
        return AuthorizationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        api: TinimalApi
    ): AuthorizationRepository {
        return AuthorizationRepositoryImpl(api)
    }



}