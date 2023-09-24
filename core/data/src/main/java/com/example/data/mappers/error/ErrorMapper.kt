package com.example.data.mappers.error

import com.example.common.UNKNOWN_ERROR
import com.example.data.state.ResultState
import com.example.network.adapter.NetworkResponse
import com.example.network.models.error.ErrorResponse

fun NetworkResponse.ApiError<ErrorResponse>.toResultError() = ResultState.Error(
    msg = body?.error?.message ?: error?.message ?: UNKNOWN_ERROR,
    throwable = error
)