package com.example.data.until.synchronizer

import com.example.data.mappers.error.toResultError
import com.example.data.state.ResultState
import com.example.network.GenericResponse
import com.example.network.adapter.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<Network : Any>(
    private val dispatcher: CoroutineDispatcher
) {

    abstract suspend fun makeNetworkCall(): GenericResponse<Network>

    abstract suspend fun saveLocally(data:Network)

    abstract suspend fun isUpdateNeeded():Boolean

    suspend fun synchronize():ResultState = withContext(dispatcher) {
        if (isUpdateNeeded()) {
            when (val response = makeNetworkCall()) {
                is NetworkResponse.ApiError -> response.toResultError()
                is NetworkResponse.Success -> {
                    saveLocally(response.body)
                    ResultState.Success
                }
            }
        }else{
            ResultState.Success
        }
    }

}