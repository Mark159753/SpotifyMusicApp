package com.example.data.repositories.recommended

import androidx.room.withTransaction
import com.example.common.di.IoDispatcher
import com.example.data.mappers.albums.toEntity
import com.example.data.mappers.albums.toModel
import com.example.data.models.albums.AlbumModel
import com.example.data.until.synchronizer.NetworkBoundResource
import com.example.data.until.update_time.UpdateTimeHelper
import com.example.localdata.db.LocalDb
import com.example.localdata.db.entities.RecommendedTracksEntity
import com.example.network.ApiService
import com.example.network.GenericResponse
import com.example.network.models.recomendations.RecommendationsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecommendedTracksRepositoryImpl @Inject constructor(
    private val db: LocalDb,
    private val apiService: ApiService,
    private val canUpdateHelper: UpdateTimeHelper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
):RecommendedTracksRepository {

    override val recommendations: Flow<List<AlbumModel>>
        get() = db.getRecommendedTracksDao().getAllItems().map { list -> list.map { it.toModel() } }

    override suspend fun syncRecommendedTracks(
        limit:Int,
        seedGenres: Array<SeedGenres>
    ) = object :NetworkBoundResource<RecommendationsResponse>(dispatcher){
        override suspend fun makeNetworkCall(): GenericResponse<RecommendationsResponse> {
            val genres = seedGenres.joinToString { it.seedName }
            return apiService.getRecommendations(limit, genres)
        }

        override suspend fun isUpdateNeeded(): Boolean = canUpdateHelper.canUpdate(RecommendedTracksEntity::class.java)

        override suspend fun saveLocally(data: RecommendationsResponse) {
            val dao = db.getRecommendedTracksDao()
            db.withTransaction {
                dao.deleteAllItems()
                dao.insertAllItems(data.tracks.map { it.toEntity() })
            }
            canUpdateHelper.save(RecommendedTracksEntity::class.java)
        }
    }.synchronize()
}