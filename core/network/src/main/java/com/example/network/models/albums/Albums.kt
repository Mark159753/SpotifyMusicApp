package com.example.network.models.albums


import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<AlbumItem>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)