package com.example.data.repositories.user

import com.example.data.models.user.UserModel
import com.example.data.state.ResultState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: Flow<UserModel?>

    suspend fun syncCurrentUser(force:Boolean = false):ResultState

}