package com.example.ui.components.collapsing_toolbar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import kotlin.math.roundToInt


@Composable
fun AlbumCollapsingToolbar(
    modifier: Modifier = Modifier,
    onScroll:()->Int = { 0 },
    onProgress:()->Float = { 0f },
    onBackClick:()->Unit = {},
//    data:LoadingData
){
    val backgroundColor = MaterialTheme.colorScheme.background

    val collapsedHeightPx = with(LocalDensity.current){ MinCollapsedHeight.toPx() }.roundToInt()

    var imageColor by remember {
        mutableStateOf(backgroundColor)
    }

    Surface(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(imageColor, backgroundColor),
                )
            )
    ) {
        Box(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth(),
        ) {
            // fraction == 1f -> Collapsed; fraction == 0f -> Expended
            val fraction = onProgress()
            val scroll = onScroll()

            val posterScale = {
//                ((1f - fraction - 0.8f) / (1f - 0.8f)).coerceIn(0f, 1f)
                1f - fraction
            }

            Log.i("FRACTION", "$fraction, onSCROLL -> ${onScroll()}, SCALE -> $posterScale")

            Spacer(modifier = Modifier
                .graphicsLayer {
                    translationY = -scroll.toFloat() / 2f
                    scaleY = posterScale()
                    scaleX = posterScale()
                    alpha = posterScale()
                }
                .background(Color.Red)
                .height(220.dp)
                .aspectRatio(1f)
                .align(Alignment.Center)
            )


//            Image(
//                painter = rememberAsyncImagePainter(model = "", imageLoader = LocalContext.current.imageLoader),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .background(Color.Red)
//                    .height(220.dp)
//                    .aspectRatio(1f)
//                    .padding(14.dp)
//                    .align(Alignment.Center)
//                    .graphicsLayer {
//                        scaleY = posterScale()
//                        scaleX = posterScale()
//                        alpha = posterScale()
//                    }
//            )

            Toolbar(
                title = "Hello",
                onBackClick = onBackClick,
                modifier = Modifier
                    .graphicsLayer {
                        translationY = -scroll.toFloat()
                    }
                )
        }

    }
}

@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
    title:String,
    onBackClick:()->Unit,
){
    Row(modifier = modifier
        .height(MinCollapsedHeight)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
            ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
        )
    }
}

sealed interface LoadingData{
    object Loading:LoadingData
    data class ToolbarData(
        val title:String,
        val posterUrl:String,
        val artists:String,
    ):LoadingData
}

private fun lerp(a: Float, b: Float, fraction: Float): Float {
    return a + fraction * (b - a)
}

private val MinCollapsedHeight = 56.dp
private val HorizontalPadding = 16.dp

private val AlbumPostrImageId = "album_poster"