package com.example.localdata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.localdata.db.converter.ArtistsListConverter
import com.example.localdata.db.dao.PlaylistDao
import com.example.localdata.db.dao.RecommendedTracksDao
import com.example.localdata.db.dao.ReleasesAlbumsDao
import com.example.localdata.db.dao.UpdateTimeDao
import com.example.localdata.db.entities.PlaylistEntity
import com.example.localdata.db.entities.RecommendedTracksEntity
import com.example.localdata.db.entities.ReleasesAlbumsEntity
import com.example.localdata.db.entities.UpdateTimeEntity

@Database(
    version = 4,
    entities = [
        ReleasesAlbumsEntity::class,
        UpdateTimeEntity::class,
        RecommendedTracksEntity::class,
        PlaylistEntity::class
    ],
    exportSchema = false
)
@TypeConverters(ArtistsListConverter::class)
abstract class LocalDb: RoomDatabase() {

    abstract fun getReleaseAlbumsDao(): ReleasesAlbumsDao
    abstract fun getRecommendedTracksDao():RecommendedTracksDao
    abstract fun getPlaylistDao():PlaylistDao
    abstract fun getUpdateTimeDao(): UpdateTimeDao

}