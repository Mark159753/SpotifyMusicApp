package com.example.network.models.auth

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("access_token")
    val accessToken:String,
    val scope:String,
    @SerializedName("expires_in")
    val expiresIn:Int,
    @SerializedName("token_type")
    val tokenType:String
)
