package com.example.network.repository.auth

import com.example.common.di.IoDispatcher
import com.example.localdata.session.SessionManager
import com.example.network.adapter.NetworkResponse
import com.example.network.auth.AuthApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val sessionManager: SessionManager,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
): AuthRepository {

    override suspend fun login(code: String) = withContext(dispatcher) {
        when(val response = authApiService.login(code)){
            is NetworkResponse.ApiError -> response
            is NetworkResponse.Success -> {
                sessionManager.saveAuthToken(response.body.accessToken)
                sessionManager.saveRefreshToken(response.body.refreshToken)
                NetworkResponse.Success(response.body.accessToken)
            }
        }
    }

    override suspend fun refreshToken() = withContext(dispatcher) {
        val refreshToken = sessionManager.refreshToken.first()
            ?: return@withContext NetworkResponse.ApiError(body = null, code = -1, error = IllegalStateException("Refresh token is null or empty"))

        when(val response = authApiService.refreshToken(refreshToken)){
            is NetworkResponse.ApiError -> {
                sessionManager.saveRefreshToken(null)
                response
            }
            is NetworkResponse.Success -> {
                sessionManager.saveAuthToken(response.body.accessToken)
                NetworkResponse.Success(response.body.accessToken)
            }
        }
    }
}