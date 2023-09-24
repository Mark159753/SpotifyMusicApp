package com.example.localdata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localdata.db.entities.RecommendedTracksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendedTracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: RecommendedTracksEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items:List<RecommendedTracksEntity>)

    @Query("SELECT * FROM recommended_tracks")
    fun getAllItems(): Flow<List<RecommendedTracksEntity>>

    @Query("SELECT * FROM recommended_tracks WHERE id = :id")
    suspend fun getItemById(id:String): RecommendedTracksEntity?

    @Query("DELETE FROM recommended_tracks")
    suspend fun deleteAllItems()
}