package com.example.spotifymusicapp

import androidx.lifecycle.ViewModel
import com.example.localdata.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    sessionManager: SessionManager
):ViewModel() {

    val isLogIn = sessionManager
        .authToken
        .map { token -> !token.isNullOrBlank() }
}