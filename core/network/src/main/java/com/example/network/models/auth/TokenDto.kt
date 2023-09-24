package com.example.network.models.auth

import okhttp3.FormBody

data class TokenDto(
    val grantType:String = "authorization_code",
    val code:String,
    val redirectUri:String
){
    fun toRequestBody() = FormBody.Builder()
        .add("grant_type", grantType)
        .add("code", code)
        .add("redirect_uri", redirectUri)
        .build()
}
