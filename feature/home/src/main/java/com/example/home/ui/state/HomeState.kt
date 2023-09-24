package com.example.home.ui.state

import androidx.compose.runtime.Stable
import com.example.data.models.albums.AlbumModel
import com.example.data.models.user.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


@Stable
class HomeState(
    private val _releasesAlbums:Flow<List<AlbumModel>>,
    private val _recommendations:Flow<List<AlbumModel>>,
    private val _user:Flow<UserModel>,
    private val scope:CoroutineScope
) {



}