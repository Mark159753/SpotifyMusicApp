package com.example.domain.model

sealed class LoadingItem<T>{
    object Loading:LoadingItem<Nothing>()
    data class Success<T>(val data:T):LoadingItem<T>()
}
