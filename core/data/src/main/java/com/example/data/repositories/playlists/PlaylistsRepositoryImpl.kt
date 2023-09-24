package com.example.data.repositories.playlists

import androidx.room.withTransaction
import com.example.common.PlaylistCategory
import com.example.common.di.IoDispatcher
import com.example.data.mappers.playlists.toEntity
import com.example.data.mappers.playlists.toModel
import com.example.data.models.playlist.PlaylistModel
import com.example.data.state.ResultState
import com.example.data.until.synchronizer.NetworkBoundResource
import com.example.data.until.update_time.UpdateTimeHelper
import com.example.localdata.db.LocalDb
import com.example.localdata.db.entities.PlaylistEntity
import com.example.network.ApiService
import com.example.network.models.playlist.PlaylistsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistsRepositoryImpl @Inject constructor(
    private val db: LocalDb,
    private val apiService: ApiService,
    private val canUpdateHelper: UpdateTimeHelper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : PlaylistsRepository {

    override fun getPlaylistByCategory(category: PlaylistCategory): Flow<List<PlaylistModel>> {
        return db.getPlaylistDao().getByCategories(category)
            .map { list ->
                list.map { item ->
                    item.toModel()
                }
            }
    }

    override suspend fun syncPlaylistByCategory(
        limit: Int,
        offset: Int,
        force: Boolean,
        category: PlaylistCategory
    ): ResultState = object : NetworkBoundResource<PlaylistsResponse>(dispatcher){
        override suspend fun makeNetworkCall() = apiService.getPlaylistByCategory(
            categoryId = category.categoryName,
            limit = limit,
            offset
        )

        override suspend fun isUpdateNeeded(): Boolean {
            return force || canUpdateHelper.canUpdate(getUpdateKey())
        }

        override suspend fun saveLocally(data: PlaylistsResponse) {
            val dao = db.getPlaylistDao()
            db.withTransaction {
                dao.deleteByCategories(category)
                dao.insertAllItems(data.playlists.items.map { it.toEntity(category) })
            }
            canUpdateHelper.save(getUpdateKey())
        }

        private fun getUpdateKey() = PlaylistEntity::class.java.name + category.categoryName
    }.synchronize()
}