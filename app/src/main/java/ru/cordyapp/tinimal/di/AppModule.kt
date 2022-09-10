package ru.cordyapp.tinimal.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.cordyapp.tinimal.data.remote.api.AuthApi
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.data.remote.repository.AuthorizationRepositoryImpl
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository
import ru.cordyapp.tinimal.domain.use_case.AuthorizationUseCase
import ru.cordyapp.tinimal.domain.use_case.CreateUserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAuthApi(okHttpClient: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://10.21.33.65:8090/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTinimalApi(okHttpClient: OkHttpClient): TinimalApi {
        return Retrofit.Builder()
            .baseUrl("http://10.21.33.65:8090/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TinimalApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor {
                Log.d("OkHTTP", it)
            }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthorizationUseCase(repository: AuthorizationRepository): AuthorizationUseCase {
        return AuthorizationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCreateUseCase(repository: AuthorizationRepository): CreateUserUseCase {
        return CreateUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        api: AuthApi
    ): AuthorizationRepository {
        return AuthorizationRepositoryImpl(api)
    }


}