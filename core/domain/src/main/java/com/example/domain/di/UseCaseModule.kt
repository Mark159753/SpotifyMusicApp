package com.example.domain.di

import com.example.common.di.IoDispatcher
import com.example.data.repositories.album.AlbumsRepository
import com.example.data.repositories.playlists.PlaylistsRepository
import com.example.domain.usecase.GetPlaylistSection
import com.example.domain.usecase.GetPlaylistsUseCase
import com.example.domain.usecase.GetReleaseAlbum
import com.example.domain.usecase.SyncPlaylistsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetPlaylistsUseCase(
        playlistRepository: PlaylistsRepository
    ):GetPlaylistsUseCase{
        return GetPlaylistsUseCase(playlistRepository)
    }

    @Provides
    fun providesGetPlaylistSection(): GetPlaylistSection {
        return GetPlaylistSection()
    }

    @Provides
    fun providesSyncPlaylistsUseCase(
        playlistRepository: PlaylistsRepository,
        @IoDispatcher
        dispatcher: CoroutineDispatcher
    ):SyncPlaylistsUseCase{
        return SyncPlaylistsUseCase(playlistRepository, dispatcher)
    }

    @Provides
    fun provideGetReleaseAlbum(
        albumsRepository: AlbumsRepository
    ): GetReleaseAlbum{
        return GetReleaseAlbum(albumsRepository)
    }
}