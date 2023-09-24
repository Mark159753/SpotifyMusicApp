package com.example.localdata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.PlaylistCategory
import com.example.localdata.db.entities.PlaylistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items:List<PlaylistEntity>)

    @Query("SELECT * FROM playlists")
    fun getAllItems(): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM playlists WHERE category = :category")
    fun getByCategories(category: PlaylistCategory): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM playlists WHERE id = :id")
    suspend fun getItemById(id:String): PlaylistEntity?

    @Query("DELETE FROM playlists")
    suspend fun deleteAllItems()

    @Query("DELETE FROM playlists WHERE category = :category")
    suspend fun deleteByCategories(category: PlaylistCategory)

}