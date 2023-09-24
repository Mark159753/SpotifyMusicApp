package com.example.network.models.album


import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("upc")
    val upc: String
)