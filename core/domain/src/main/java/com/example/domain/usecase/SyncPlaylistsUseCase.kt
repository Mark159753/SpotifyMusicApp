package com.example.domain.usecase

import com.example.common.PlaylistCategory
import com.example.common.di.IoDispatcher
import com.example.data.repositories.playlists.PlaylistsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncPlaylistsUseCase @Inject constructor(
    private val playlistRepository: PlaylistsRepository,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(input:List<PlaylistCategory>) = withContext(dispatcher){
        input.map { category ->
            async {
                playlistRepository.syncPlaylistByCategory(category = category)
            }
        }.awaitAll()
    }
}