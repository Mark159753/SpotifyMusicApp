package com.example.data.state

sealed interface ResultState{
    object Success:ResultState

    data class Error(val msg:String?, val throwable: Throwable?):ResultState
}