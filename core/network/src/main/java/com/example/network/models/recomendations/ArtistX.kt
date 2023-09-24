package com.example.network.models.recomendations


import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsXXX,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)