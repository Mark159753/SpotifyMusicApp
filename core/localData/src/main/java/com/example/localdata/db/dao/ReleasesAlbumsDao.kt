package com.example.localdata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localdata.db.entities.ReleasesAlbumsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReleasesAlbumsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ReleasesAlbumsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items:List<ReleasesAlbumsEntity>)

    @Query("SELECT * FROM releases_albums")
    fun getAllItems():Flow<List<ReleasesAlbumsEntity>>

    @Query("SELECT * FROM releases_albums WHERE id = :id")
    suspend fun getItemById(id:String): ReleasesAlbumsEntity?

    @Query("DELETE FROM releases_albums")
    suspend fun deleteAllItems()

}