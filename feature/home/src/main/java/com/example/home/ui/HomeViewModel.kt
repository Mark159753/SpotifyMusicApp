package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UNKNOWN_ERROR
import com.example.data.repositories.album.AlbumsRepository
import com.example.data.repositories.recommended.RecommendedTracksRepository
import com.example.data.repositories.user.UserRepository
import com.example.data.state.ResultState
import com.example.domain.usecase.GetPlaylistSection
import com.example.domain.usecase.GetPlaylistsUseCase
import com.example.domain.usecase.GetReleaseAlbum
import com.example.domain.usecase.SyncPlaylistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val albumsRepository: AlbumsRepository,
    private val userRepository: UserRepository,
    private val recommendedTracksRepository: RecommendedTracksRepository,
    private val syncPlaylistsUseCase: SyncPlaylistsUseCase,
    getPlaylists: GetPlaylistsUseCase,
    getPlaylistSection: GetPlaylistSection,
    getReleaseAlbum: GetReleaseAlbum
):ViewModel() {

    private val playlistSections = getPlaylistSection()

    private val _errorsMsg = Channel<String>()
    val errorsMsg: Flow<String>
        get() = _errorsMsg.receiveAsFlow()

    val releasesAlbums = getReleaseAlbum(viewModelScope)

    val recommendations = recommendedTracksRepository.recommendations

    val user = userRepository.user

    val playLists = getPlaylists(playlistSections, viewModelScope)

    init {
        syncUser()
        syncReleaseAlbum()
        syncPlaylists()
        syncRecommendedTracks()
    }

    private fun syncUser(){
        viewModelScope.launch {
            handleError(
                userRepository.syncCurrentUser()
            )
        }
    }

    private fun syncPlaylists(){
        viewModelScope.launch {
            val categories = playlistSections.map { it.category }

            val error = StringBuilder()

            syncPlaylistsUseCase(categories)
                .filterIsInstance<ResultState.Error>()
                .forEach { e ->
                    error.append(e.msg ?: e.throwable?.message ?: UNKNOWN_ERROR)
                }
            if (error.isNotBlank()){
                _errorsMsg.send(error.toString())
            }
        }
    }

    private fun syncRecommendedTracks(){
        viewModelScope.launch {
            handleError(
                recommendedTracksRepository.syncRecommendedTracks()
            )
        }
    }

    private fun syncReleaseAlbum(){
        viewModelScope.launch {
            handleError(
                albumsRepository.syncReleasesAlbums()
            )
        }
    }

    private fun handleError(resultState: ResultState){
        if (resultState is ResultState.Error){
            viewModelScope.launch {
                _errorsMsg.send(resultState.msg ?: resultState.throwable?.message ?: UNKNOWN_ERROR)
            }
        }
    }
}