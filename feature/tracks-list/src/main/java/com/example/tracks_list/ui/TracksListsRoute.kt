package com.example.tracks_list.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.components.CollapsingScaffold
import com.example.ui.components.collapsing_toolbar.AlbumCollapsingToolbar
import com.example.ui.components.rememberCollapsingScaffoldState
import kotlin.math.roundToInt

@Composable
fun TracksListsRoute(
    onNavBack:()->Unit,
    viewModel: TracksListViewModel = hiltViewModel()
){

    TracksLists(
        onNavBack = onNavBack
    )
}

@Composable
private fun TracksLists(
    onNavBack:()->Unit,
){

    val collapsingState = rememberCollapsingScaffoldState(
        minHeight = 52.dp
    )

    CollapsingScaffold(
        state = collapsingState,
        topBar = {
            AlbumCollapsingToolbar(
                onScroll = { collapsingState.scrollOffset.roundToInt() },
                onProgress = { collapsingState.process }
            )
        }
    ){
        LazyColumn(
            modifier = Modifier
        ){
            repeat(100){ i ->
                item(
                    key = i
                ){
                    Text(
                        text = "POS -> $i",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                    )
                }
            }
        }
    }

//    Box(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.background)
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ){
//        Text(text = "Tracks List Screen")
//    }
}