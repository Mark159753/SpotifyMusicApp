package com.example.data.mappers.user

import com.example.data.models.user.UserModel
import com.example.localdata.proto.user.UserData
import com.example.network.models.user.UserResponse
import com.example.spotifymusicapp.LocalUser

fun UserData.toModel() = UserModel(
    id = id,
    name = name,
    email = email
)

fun UserResponse.toData() = UserData(
    id = id,
    name = displayName,
    email = email
)

fun UserResponse.toModel() = UserModel(
    id = id,
    name = displayName,
    email = email
)