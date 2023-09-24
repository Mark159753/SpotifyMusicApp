package com.example.localdata.di

import android.content.Context
import com.example.localdata.proto.user.LocalUserDataStore
import com.example.localdata.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context) = SessionManager(context)

    @Provides
    @Singleton
    fun provideLocalUserDataStore(@ApplicationContext context: Context) = LocalUserDataStore(context)

}