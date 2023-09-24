package com.example.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UNKNOWN_ERROR
import com.example.localdata.session.SessionManager
import com.example.network.adapter.NetworkResponse
import com.example.network.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _action = Channel<LoginAction>()
    val action: Flow<LoginAction>
        get() = _action.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading:StateFlow<Boolean>
        get() = _isLoading

    fun onLogin(code:String){
        viewModelScope.launch {
            _isLoading.value = true
            when(val response = authRepository.login(code)){
                is NetworkResponse.ApiError -> {
                    _isLoading.value = false
                    _action.send(LoginAction.Error(
                        response.body?.error?.message ?: response.error?.message ?: UNKNOWN_ERROR
                    ))
                }
                is NetworkResponse.Success -> {
                    _isLoading.value = false
                    _action.send(LoginAction.NavToHome)
                }
            }
        }
    }
    
}

sealed interface LoginAction{
    object NavToHome:LoginAction
    data class Error(val msg:String):LoginAction
}