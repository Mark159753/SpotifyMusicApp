package com.example.network.models.user


import com.google.gson.annotations.SerializedName

data class Followers(
    @SerializedName("href")
    val href: Any,
    @SerializedName("total")
    val total: Int
)