package com.example.localdata.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.PlaylistCategory

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name:String,
    val description:String,
    val image:String,
    val type:String,
    val category: PlaylistCategory = PlaylistCategory.None
)
