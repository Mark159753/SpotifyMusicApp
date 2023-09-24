package com.example.network.models.albums


import com.google.gson.annotations.SerializedName

data class ExternalUrlsX(
    @SerializedName("spotify")
    val spotify: String
)