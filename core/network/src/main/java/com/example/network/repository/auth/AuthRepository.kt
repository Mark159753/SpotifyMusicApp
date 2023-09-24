package com.example.network.repository.auth

import com.example.network.GenericResponse

interface AuthRepository {

    suspend fun login(code:String): GenericResponse<String>

    suspend fun refreshToken():GenericResponse<String>
}