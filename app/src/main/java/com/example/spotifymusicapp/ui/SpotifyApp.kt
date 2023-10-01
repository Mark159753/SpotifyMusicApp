package com.example.spotifymusicapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.home.navigation.HomeNavigationRoute
import com.example.login.navigation.LoginNavigationRoute
import com.example.spotifymusicapp.navigation.BottomBarDestinations
import com.example.spotifymusicapp.navigation.SpotifyNavHost
import com.example.ui.components.SpotifyBottomNavigation
import com.example.ui.components.SpotifyNavigationBarItem

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SpotifyApp(
    modifier: Modifier = Modifier,
    appState:SpotifyAppState = rememberSpotifyAppState(),
    isAuth:Boolean = false
){
    Scaffold(
        modifier = modifier
            .semantics {
                       testTagsAsResourceId = true
            },
        bottomBar = {
            val isVisible = appState.currentBottomBarDestination != null

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    animationSpec = tween(
                        durationMillis = 200,
                    ),
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(
                        delayMillis = 50,
                        durationMillis = 200,
                    ),
                    targetOffsetY = { it }
                )
            ) {
                BottomNavBar(
                    destinations = appState.bottomBarDestinations,
                    onNavigateToDestination = appState::navigateToBottomBarDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) { paddingValues ->
        SpotifyNavHost(
            navController = appState.navController,
            startDestination = getStartDestination(isAuth),
            contentPadding = paddingValues
        )
    }
}

private fun getStartDestination(isAuth:Boolean):String{
    return if (isAuth) HomeNavigationRoute else LoginNavigationRoute
}

@Composable
private fun BottomNavBar(
    destinations: List<BottomBarDestinations>,
    onNavigateToDestination: (BottomBarDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
){
    SpotifyBottomNavigation(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isBottomBarDestinationSelected(destination)
            SpotifyNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIconId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.selectedIconId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) },
            )
        }
    }
}

private fun NavDestination?.isBottomBarDestinationSelected(destination: BottomBarDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false