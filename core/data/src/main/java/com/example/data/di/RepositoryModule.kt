package com.example.data.di

import com.example.data.repositories.album.AlbumsRepository
import com.example.data.repositories.album.AlbumsRepositoryImp
import com.example.data.repositories.playlists.PlaylistsRepository
import com.example.data.repositories.playlists.PlaylistsRepositoryImpl
import com.example.data.repositories.recommended.RecommendedTracksRepository
import com.example.data.repositories.recommended.RecommendedTracksRepositoryImpl
import com.example.data.repositories.user.UserRepository
import com.example.data.repositories.user.UserRepositoryImpl
import com.example.data.until.update_time.UpdateTimeHelper
import com.example.data.until.update_time.UpdateTimeHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUpdateTimeHelper(
        helper:UpdateTimeHelperImpl
    ): UpdateTimeHelper

    @Binds
    abstract fun bindAlbumsRepository(
        repository: AlbumsRepositoryImp
    ): AlbumsRepository

    @Binds
    abstract fun bindUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindRecommendedTracksRepository(
        repository: RecommendedTracksRepositoryImpl
    ): RecommendedTracksRepository

    @Binds
    abstract fun bindPlaylistsRepository(
        repository: PlaylistsRepositoryImpl
    ): PlaylistsRepository
}