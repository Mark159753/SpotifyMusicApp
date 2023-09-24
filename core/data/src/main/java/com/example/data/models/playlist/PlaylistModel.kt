package com.example.data.models.playlist

import com.example.common.PlaylistCategory

data class PlaylistModel(
    val id:String,
    val name:String,
    val description:String,
    val image:String,
    val type:String,
    val category: PlaylistCategory = PlaylistCategory.None
){

    companion object{
        fun getTestModel(id:String = "") = PlaylistModel(
            id = id,
            name = "Top lists",
            description = "Zach Bryan & Kacey Musgraves are on top of the Hottest 50!",
            image = "",
            type = "playlist",
            category = PlaylistCategory.None
        )
    }
}
