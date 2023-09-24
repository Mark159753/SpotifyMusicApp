package com.example.data.repositories.recommended

import com.example.data.models.albums.AlbumModel
import com.example.data.state.ResultState
import kotlinx.coroutines.flow.Flow

interface RecommendedTracksRepository {

    val recommendations:Flow<List<AlbumModel>>

    suspend fun syncRecommendedTracks(
        limit:Int = 6,
        seedGenres:Array<SeedGenres> = arrayOf(
            SeedGenres.Pop,
            SeedGenres.Rock,
            SeedGenres.Club,
            SeedGenres.IndiePop,
            SeedGenres.JRock
        )
    ):ResultState

}

enum class SeedGenres(val seedName:String){
    Pop(seedName = "pop"),
    Rock(seedName = "rock"),
    Club(seedName = "club"),
    IndiePop(seedName = "indie-pop"),
    JRock(seedName = "j-rock")
}