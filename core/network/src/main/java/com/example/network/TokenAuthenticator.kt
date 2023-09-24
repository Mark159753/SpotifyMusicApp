package com.example.network

import android.util.Log
import com.example.common.AUTH_TOKEN_TYPE
import com.example.localdata.session.SessionManager
import com.example.network.adapter.NetworkResponse
import com.example.network.repository.auth.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val refreshTokenRepository: AuthRepository
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return refreshToken()?.takeIf { it.isNotBlank() }?.let { token ->
            response.request.newBuilder()
                .header("Authorization", "$AUTH_TOKEN_TYPE $token")
                .build()
        }
    }

    private fun refreshToken():String? = runBlocking {
        when(val response = refreshTokenRepository.refreshToken()){
            is NetworkResponse.ApiError -> {
                Log.e("TokenAuthenticator", "refreshToken -> ${response.body?.error ?: response.error?.message}")
                null
            }
            is NetworkResponse.Success -> response.body
        }
    }
}