package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.data.models.albums.AlbumModel
import com.example.data.models.albums.Artist

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    item:AlbumModel
){
    Column(
        modifier = modifier
            .width(180.dp)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = item.name,
            imageLoader = LocalContext.current.imageLoader,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .aspectRatio(1f)
            )

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = item.type,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.artists.joinToString { it.name },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun AlbumItemPreview(){
    val album = AlbumModel(
        id = "",
        name = "Cool music album",
        image = "",
        type = "album",
        releaseDate = "today",
        totalTracks = 5,
        artists = listOf(
            Artist(
                id = "",
                name = "Mark",
                type = "artist"
            ),
            Artist(
                id = "",
                name = "Justin",
                type = "artist"
            )
        )
    )
    AlbumItem(
        item = album
    )
}