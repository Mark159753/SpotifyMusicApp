package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.data.models.albums.AlbumModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecommendationBlock(
    modifier: Modifier = Modifier,
    items:ImmutableList<AlbumModel>
){
    FlowRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        maxItemsInEachRow = 2
    ) {
        items.forEach { item ->
            RecommendationItem(
                model = item,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun RecommendationItem(
    modifier: Modifier = Modifier,
    model:AlbumModel
){
    val context = LocalContext.current

    Row(
        modifier = modifier
            .height(62.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = model.image,
            contentDescription = model.name,
            contentScale = ContentScale.Crop,
            imageLoader = context.imageLoader,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        )

        Text(
            text = model.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


@Preview
@Composable
private fun RecommendationBlockPreview(){
    val data = Array(6){ AlbumModel.getTestModel() }
    RecommendationBlock(
        items = data.toList().toImmutableList()
    )
}

