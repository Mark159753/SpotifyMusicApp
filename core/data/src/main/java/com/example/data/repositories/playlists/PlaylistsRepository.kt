package com.example.data.repositories.playlists

import com.example.common.PlaylistCategory
import com.example.data.models.playlist.PlaylistModel
import com.example.data.state.ResultState
import kotlinx.coroutines.flow.Flow

interface PlaylistsRepository {

    fun getPlaylistByCategory(category:PlaylistCategory): Flow<List<PlaylistModel>>

    suspend fun syncPlaylistByCategory(
        limit: Int = 16,
        offset: Int = 0,
        force:Boolean = false,
        category: PlaylistCategory,
        ):ResultState
}