package com.example.tracks_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

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
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Tracks List Screen")
    }
}