package com.example.network.models.recomendations


import com.google.gson.annotations.SerializedName

data class Seed(
    @SerializedName("afterFilteringSize")
    val afterFilteringSize: Int,
    @SerializedName("afterRelinkingSize")
    val afterRelinkingSize: Int,
    @SerializedName("href")
    val href: Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("initialPoolSize")
    val initialPoolSize: Int,
    @SerializedName("type")
    val type: String
)