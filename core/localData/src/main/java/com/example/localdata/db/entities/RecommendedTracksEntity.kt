package com.example.localdata.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommended_tracks")
data class RecommendedTracksEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name:String,
    val image:String,
    val type:String,
    val releaseDate:String,
    val totalTracks:Int,
    val artists:List<Artist>
)
