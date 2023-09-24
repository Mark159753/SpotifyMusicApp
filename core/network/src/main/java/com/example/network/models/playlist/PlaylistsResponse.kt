package com.example.network.models.playlist


import com.google.gson.annotations.SerializedName

data class PlaylistsResponse(
    @SerializedName("playlists")
    val playlists: Playlists
)