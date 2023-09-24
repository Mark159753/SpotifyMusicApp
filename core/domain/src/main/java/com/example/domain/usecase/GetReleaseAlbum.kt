package com.example.domain.usecase

import com.example.data.models.albums.AlbumModel
import com.example.data.repositories.album.AlbumsRepository
import com.example.domain.model.LoadingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetReleaseAlbum @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {

    operator fun invoke(
        scope: CoroutineScope
    ): StateFlow<List<LoadingItem<out AlbumModel>>> {
        return albumsRepository
            .releasesAlbums
            .map { list ->
                if (list.isEmpty()){
                    Array(10){ LoadingItem.Loading}.toList()
                }else{
                    list.map { item ->
                        LoadingItem.Success(
                            data = item
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