package com.example.localdata.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "releases_albums")
data class ReleasesAlbumsEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name:String,
    val image:String,
    val type:String,
    val releaseDate:String,
    val totalTracks:Int,
    val artists:List<Artist>
)

data class Artist(
    val id:String,
    val name:String,
    val type:String
)
