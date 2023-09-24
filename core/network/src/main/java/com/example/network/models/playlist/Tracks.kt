package com.example.network.models.playlist


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("href")
    val href: String,
    @SerializedName("total")
    val total: Int
)