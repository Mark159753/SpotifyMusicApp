package com.example.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.models.albums.AlbumModel
import com.example.data.models.playlist.PlaylistModel
import com.example.domain.model.LoadingItem
import com.example.domain.model.PlaylistWithCategory
import com.example.home.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val releasesAlbums by viewModel.releasesAlbums.collectAsStateWithLifecycle(initialValue = emptyList())
    val recommendations by viewModel.recommendations.collectAsStateWithLifecycle(initialValue = emptyList())
    val user by viewModel.user.collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        viewModel.errorsMsg.collectLatest { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    HomeScreen(
        releasesAlbums = releasesAlbums.toImmutableList(),
        recommendations = recommendations.toImmutableList(),
        playListsSections = viewModel.playLists.toImmutableList(),
        userName = user?.name ?: "",
        contentPadding = contentPadding
    )
}

@Composable
fun HomeScreen(
    releasesAlbums: ImmutableList<LoadingItem<out AlbumModel>>,
    recommendations:ImmutableList<AlbumModel>,
    playListsSections:ImmutableList<PlaylistWithCategory<PlaylistModel>>,
    userName:String = "",
    contentPadding: PaddingValues = PaddingValues()
){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding())
    ){
        item(
            key = "header"
        ) {
            Header(
                userName = userName,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                )
        }

        item(
            key = "recommendation"
        ) {
            RecommendationBlock(
                items = recommendations,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }

        LatestReleases(
            releasesAlbums = releasesAlbums
        )

        playListsSections.forEach { listItem ->
            PlaylistsSection(
                data = listItem
            )
        }

    }
}


fun LazyListScope.LatestReleases(
    modifier: Modifier = Modifier,
    releasesAlbums: ImmutableList<LoadingItem<out AlbumModel>>
){
    item(
        key = "latest_releases"
    ){

        val isLoading by remember(releasesAlbums) {
            derivedStateOf {
                val item = releasesAlbums.firstOrNull()
                item == null || item is LoadingItem.Loading
            }
        }

        SectionTitle(
            isLoading = isLoading,
            title = R.string.new_releases_title
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ){
            releasesAlbums.forEach { album ->
                item(
                    key = if (album is LoadingItem.Success) album.data.id else null,
                ){
                    when (album) {
                        LoadingItem.Loading -> LoadingPlaylist()
                        is LoadingItem.Success -> AlbumItem(
                            item = album.data
                        )
                    }
                }
            }
        }
    }

}