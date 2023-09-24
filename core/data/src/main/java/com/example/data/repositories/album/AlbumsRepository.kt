package com.example.data.repositories.album

import com.example.data.models.albums.AlbumModel
import com.example.data.state.ResultState
import com.example.network.GenericResponse
import com.example.network.adapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    val releasesAlbums:Flow<List<AlbumModel>>

    suspend fun syncReleasesAlbums(
        force:Boolean = false,
        limit:Int = 16,
        offset:Int = 0
    ): ResultState

    suspend fun getAlbumById(id:String): GenericResponse<AlbumModel>

    suspend fun getAlbumsTracks(
        albumId:String,
        limit:Int = 20,
        offset:Int = 0
    )
}