package com.example.data.repositories.album

import androidx.room.withTransaction
import com.example.common.di.IoDispatcher
import com.example.data.mappers.albums.toModel
import com.example.data.mappers.albums.toReleaseAlbumEntity
import com.example.data.models.albums.AlbumModel
import com.example.data.until.synchronizer.NetworkBoundResource
import com.example.data.until.update_time.UpdateTimeHelper
import com.example.localdata.db.LocalDb
import com.example.localdata.db.entities.ReleasesAlbumsEntity
import com.example.network.ApiService
import com.example.network.adapter.NetworkResponse
import com.example.network.models.albums.AlbumsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val db:LocalDb,
    private val apiService: ApiService,
    private val canUpdateHelper: UpdateTimeHelper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
): AlbumsRepository {

    override val releasesAlbums: Flow<List<AlbumModel>>
        get() = db.getReleaseAlbumsDao().getAllItems().map { it.map { it.toModel() } }

    override suspend fun syncReleasesAlbums(
        force:Boolean,
        limit: Int,
        offset: Int
    ) = object :NetworkBoundResource<AlbumsResponse>(dispatcher){
        override suspend fun makeNetworkCall() = apiService.getNewAlbumsReleases(
            limit = limit,
            offset = offset
        )
        override suspend fun isUpdateNeeded() = force || canUpdateHelper.canUpdate(ReleasesAlbumsEntity::class.java)

        override suspend fun saveLocally(data: AlbumsResponse) {
            val entities = data.albums.items.map { it.toReleaseAlbumEntity() }
            db.withTransaction {
                db.getReleaseAlbumsDao().deleteAllItems()
                db.getReleaseAlbumsDao().insertAllItems(entities)
            }
            canUpdateHelper.save(ReleasesAlbumsEntity::class.java)
        }
    }.synchronize()

    override suspend fun getAlbumById(id: String) = withContext(dispatcher){
        when(val result = apiService.getAlbum(id)){
            is NetworkResponse.ApiError -> result
            is NetworkResponse.Success -> NetworkResponse.Success(result.body.toModel())
        }
    }

    override suspend fun getAlbumsTracks(
        albumId: String,
        limit: Int,
        offset: Int
    ) {
        withContext(dispatcher){
            val result = apiService.getAlbumsTracts(
                id = albumId,
                limit = limit,
                offset = offset
            )

            when(result){
                is NetworkResponse.ApiError -> {}
                is NetworkResponse.Success -> {}
            }
        }
    }
}