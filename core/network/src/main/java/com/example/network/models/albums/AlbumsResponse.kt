package com.example.network.models.albums


import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("albums")
    val albums: Albums
)