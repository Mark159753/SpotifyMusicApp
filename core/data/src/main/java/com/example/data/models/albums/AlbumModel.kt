package com.example.data.models.albums

data class AlbumModel(
    val id:String,
    val name:String,
    val image:String,
    val type:String,
    val releaseDate:String,
    val totalTracks:Int,
    val artists:List<Artist>
){
    companion object{
        fun getTestModel() = AlbumModel(
            id = "",
            name = "Some cool Album name",
            image = "",
            type = "album",
            releaseDate = "today",
            totalTracks = 10,
            artists = listOf(Artist(id = "", name = "Paul", type = "artist"))
        )
    }
}

data class Artist(
    val id:String,
    val name: String,
    val type:String
)
