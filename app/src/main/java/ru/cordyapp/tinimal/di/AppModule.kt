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
import ru.cordyapp.tinimal.data.remote.AddTokenHeaderInterceptor
import ru.cordyapp.tinimal.data.remote.api.AuthApi
import ru.cordyapp.tinimal.data.remote.api.TinimalApi
import ru.cordyapp.tinimal.data.remote.repository.AuthorizationRepositoryImpl
import ru.cordyapp.tinimal.data.remote.repository.TiNimalRepositoryImpl
import ru.cordyapp.tinimal.domain.repository.AuthorizationRepository
import ru.cordyapp.tinimal.domain.repository.TiNimalRepository
import ru.cordyapp.tinimal.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("https://cordy-app.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTinimalApi(okHttpClient: OkHttpClient): TinimalApi {
        return Retrofit.Builder()
            .baseUrl("https://cordy-app.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TinimalApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        val tokenHeaderInterceptor = AddTokenHeaderInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(tokenHeaderInterceptor)
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
    fun provideGetCats(repository: TiNimalRepository): GetCatsListByUserUseCase {
        return GetCatsListByUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUsers(repository: TiNimalRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddCat(repository: TiNimalRepository): AddCatUseCase {
        return AddCatUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateCat(repository: TiNimalRepository): UpdateCatUseCase {
        return UpdateCatUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostAvatar(repository: TiNimalRepository): PostAvatarUseCase {
        return PostAvatarUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteUser(repository: TiNimalRepository): DeleteUserUseCase {
        return DeleteUserUseCase(repository)
    }



    @Provides
    @Singleton
    fun provideGetFeedback(repository: TiNimalRepository): GetFeedbackUseCase {
        return GetFeedbackUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCatById(repository: TiNimalRepository): GetCatByIdUseCase {
        return GetCatByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateUser(repository: TiNimalRepository): UpdateUserUseCase {
        return UpdateUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFavoritesCats(repository: TiNimalRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCatAvatar(repository: TiNimalRepository): PostCatAvatarUseCase {
        return PostCatAvatarUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        api: AuthApi
    ): AuthorizationRepository {
        return AuthorizationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTinimalRepository(
        api: TinimalApi
    ): TiNimalRepository {
        return TiNimalRepositoryImpl(api)
    }


}