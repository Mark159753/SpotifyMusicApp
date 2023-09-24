package com.example.network.auth

import android.util.Log
import com.example.common.BASIC_TOKEN_TYPE
import com.example.common.BuildConfig
import com.example.network.GenericResponse
import com.example.network.adapter.NetworkResponse
import com.example.network.models.auth.RefreshTokenDto
import com.example.network.models.auth.RefreshTokenResponse
import com.example.network.models.auth.TokenDto
import com.example.network.models.auth.TokenResponse
import com.example.network.models.error.ErrorResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Base64

class AuthApiServiceImpl: AuthApiService {

    private val gson = Gson()

    override suspend fun login(code: String): GenericResponse<TokenResponse> {
        val url = BuildConfig.AUTH_URL
        val dto = TokenDto(
            grantType = "authorization_code",
            code = code,
            redirectUri = BuildConfig.RedirectUri
        ).toRequestBody()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", getAuthorizationToken())
            .post(dto)
            .build()

        Log.i("AuthApiServiceImpl", "login $url")
        return try {
            val res = client.newCall(request).execute()
            when{
                res.isSuccessful && res.body != null -> {
                    val data = gson.fromJson(res.body!!.string(), TokenResponse::class.java)
                    NetworkResponse.Success(body = data)
                }
                res.body == null -> NetworkResponse.ApiError(body = null, code = res.code)
                else -> {
                    val errorResponse = res.body?.string()
                    val errorBody = if (!errorResponse.isNullOrBlank()){
                        gson.fromJson(errorResponse, ErrorResponse::class.java)
                    } else null
                    NetworkResponse.ApiError(body = errorBody, code = res.code)
                }
            }
        }catch (e:Exception){
            Log.e("ERROR", e.stackTraceToString())
            NetworkResponse.ApiError(body = null, code = -1, error = e)
        }
    }

    override suspend fun refreshToken(refreshToken: String): GenericResponse<RefreshTokenResponse> {
        val url = BuildConfig.AUTH_URL
        val dto = RefreshTokenDto(
            refreshToken = refreshToken
        ).toRequestBody()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", getAuthorizationToken())
            .post(dto)
            .build()

        Log.i("AuthApiServiceImpl", "refreshToken $url")

        return try {
            val res = client.newCall(request).execute()
            when{
                res.isSuccessful && res.body != null -> {
                    val data = gson.fromJson(res.body!!.string(), RefreshTokenResponse::class.java)
                    NetworkResponse.Success(body = data)
                }
                res.body == null -> NetworkResponse.ApiError(body = null, code = res.code)
                else -> {
                    val errorResponse = res.body?.string()
                    val errorBody = if (!errorResponse.isNullOrBlank()){
                        gson.fromJson(errorResponse, ErrorResponse::class.java)
                    } else null
                    NetworkResponse.ApiError(body = errorBody, code = res.code)
                }
            }
        }catch (e:Exception){
            Log.e("ERROR", e.stackTraceToString())
            NetworkResponse.ApiError(body = null, code = -1, error = e)
        }
    }

    private fun getAuthorizationToken():String{
        return "$BASIC_TOKEN_TYPE " + "${BuildConfig.ClientId}:${BuildConfig.ClientSecret}".encodeBase64()
    }

    private fun String.encodeBase64() = Base64.getEncoder().encodeToString(this.toByteArray())
}