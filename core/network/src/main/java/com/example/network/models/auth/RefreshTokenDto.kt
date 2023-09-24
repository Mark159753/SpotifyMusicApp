package com.example.network.models.auth

import okhttp3.FormBody

data class RefreshTokenDto(
    val grantType:String = "refresh_token",
    val refreshToken:String,
){
    fun toRequestBody() = FormBody.Builder()
        .add("grant_type", grantType)
        .add("refresh_token", refreshToken)
        .build()
}
