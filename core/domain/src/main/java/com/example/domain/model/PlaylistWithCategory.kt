package com.example.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.StateFlow

@Stable
data class PlaylistWithCategory<out T>(
    @StringRes
    val categoryName:Int,
    val list: StateFlow<List<LoadingItem<out T>>>
)
