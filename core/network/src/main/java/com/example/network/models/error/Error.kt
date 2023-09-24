package com.example.network.models.error


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)