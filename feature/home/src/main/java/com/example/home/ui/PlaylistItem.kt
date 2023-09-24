package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.data.models.playlist.PlaylistModel
import com.example.domain.model.LoadingItem
import com.example.domain.model.PlaylistWithCategory
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder


fun LazyListScope.PlaylistsSection(
    modifier: Modifier = Modifier,
    data: PlaylistWithCategory<PlaylistModel>
){
    item(
        key = data.categoryName
    ) {
        val playlist by data.list.collectAsStateWithLifecycle()

        val isLoading by remember {
            derivedStateOf {
                val item = playlist.firstOrNull()
                item == null || item is LoadingItem.Loading
            }
        }

        SectionTitle(
            isLoading = isLoading,
            title = data.categoryName
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            playlist.forEach { item ->
                item(
                    key = if (item is LoadingItem.Success) item.data.id else null,
                ) {
                    when (item) {
                        LoadingItem.Loading -> LoadingPlaylist()
                        is LoadingItem.Success -> PlaylistItem(item = item.data)
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingPlaylist(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .width(170.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .placeholder(
                    visible = true,
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp),
                    highlight = PlaceholderHighlight.fade()
                )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth(0.9f)
                .placeholder(
                    visible = true,
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp),
                    highlight = PlaceholderHighlight.fade()
                )
        )

        Spacer(modifier = Modifier.height(3.dp))

        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth(0.6f)
                .placeholder(
                    visible = true,
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp),
                    highlight = PlaceholderHighlight.fade()
                )
        )
    }
}

@Composable
private fun PlaylistItem(
    modifier: Modifier = Modifier,
    item:PlaylistModel
){

    Column(
        modifier = modifier
            .width(170.dp)
    ) {

        AsyncImage(
            model = item.image,
            contentDescription = item.name,
            imageLoader = LocalContext.current.imageLoader,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = item.description.ifBlank { item.name },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
        )
    }
}

@Preview
@Composable
private fun PlaylistsSectionPreview(){
    PlaylistItem(item = PlaylistModel.getTestModel())
}

@Preview
@Composable
private fun PlaylistsLoadingPreview(){
    LoadingPlaylist()
}