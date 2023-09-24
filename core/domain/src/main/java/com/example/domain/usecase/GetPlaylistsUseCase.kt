package com.example.domain.usecase

import com.example.common.PlaylistCategory
import com.example.data.models.playlist.PlaylistModel
import com.example.data.repositories.playlists.PlaylistsRepository
import com.example.domain.model.LoadingItem
import com.example.domain.model.PlaylistWithCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val playlistRepository: PlaylistsRepository
) {

    operator fun invoke(
        input: List<PlayListSection>,
        scope: CoroutineScope
    ) = input.map { inputData ->
        PlaylistWithCategory(
            categoryName = inputData.title,
            list = getPlayLists(inputData.category, scope)
        )
    }

   private fun getPlayLists(
       categories:PlaylistCategory,
       scope: CoroutineScope
   ): StateFlow<List<LoadingItem<out PlaylistModel>>> {
       return playlistRepository
           .getPlaylistByCategory(categories)
           .map { list ->
               if (list.isEmpty())
                   Array(10){ LoadingItem.Loading}.toList()
               else{
                   list.map { album ->
                       LoadingItem.Success(
                           data = album
                       )
                   }
               }
           }
           .stateIn(
               scope = scope,
               started = SharingStarted.WhileSubscribed(5_000),
               initialValue = Array(10){ LoadingItem.Loading}.toList()
           )
   }
}