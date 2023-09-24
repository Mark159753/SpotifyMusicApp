package com.example.network.auth

import com.example.network.GenericResponse
import com.example.network.models.auth.RefreshTokenResponse
import com.example.network.models.auth.TokenResponse

interface AuthApiService {

    suspend fun login(code:String):GenericResponse<TokenResponse>

    suspend fun refreshToken(refreshToken:String):GenericResponse<RefreshTokenResponse>
}