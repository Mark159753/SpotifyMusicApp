package com.example.network.di

import com.example.common.BuildConfig
import com.example.common.di.IoDispatcher
import com.example.localdata.session.SessionManager
import com.example.network.ApiService
import com.example.network.AuthenticationInterceptor
import com.example.network.TokenAuthenticator
import com.example.network.adapter.NetworkResponseAdapterFactory
import com.example.network.auth.AuthApiService
import com.example.network.auth.AuthApiServiceImpl
import com.example.network.repository.auth.AuthRepository
import com.example.network.repository.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideAuthenticationInterceptor(sessionManager: SessionManager): AuthenticationInterceptor{
        return AuthenticationInterceptor(sessionManager)
    }

    @Provides
    fun provideTokenAuthenticator(repository: AuthRepository): TokenAuthenticator {
        return TokenAuthenticator(repository)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logger.level = HttpLoggingInterceptor.Level.BODY
        else logger.level = HttpLoggingInterceptor.Level.BASIC
        return logger
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        authenticationInterceptor:AuthenticationInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        client: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideAuthApiService(): AuthApiService{
        return AuthApiServiceImpl()
    }

    @Provides
    fun provideAuthRepository(
        authApiService: AuthApiService,
        sessionManager: SessionManager,
        @IoDispatcher
        dispatcher: CoroutineDispatcher
    ): AuthRepository{
        return AuthRepositoryImpl(
            authApiService, sessionManager, dispatcher
        )
    }
}