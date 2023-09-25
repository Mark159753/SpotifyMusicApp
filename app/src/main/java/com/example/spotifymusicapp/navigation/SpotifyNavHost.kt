package com.example.spotifymusicapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.favorites.navigation.favoritesScreen
import com.example.home.navigation.homeScreen
import com.example.home.navigation.navigateToHome
import com.example.login.navigation.LoginNavigationRoute
import com.example.login.navigation.loginScreen
import com.example.search.navigation.searchScreen
import com.example.tracks_list.navigation.navigateToTracksList
import com.example.tracks_list.navigation.tracksListScreen

@Composable
fun SpotifyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = LoginNavigationRoute,
    contentPadding: PaddingValues = PaddingValues()
){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
        ){

        loginScreen(
            onNavigateToHome = {
                if (navController.currentBackStackEntry?.lifecycleIsResumed() == true){
                    navController.navigateToHome(
                        NavOptions.Builder()
                            .setPopUpTo(
                                navController.graph.id,
                                inclusive = true
                            )
                            .build()
                    )
                }
            }
        )

        homeScreen(
            contentPadding = contentPadding,
            onNavToTracksList = { album ->
                if (navController.currentBackStackEntry?.lifecycleIsResumed() == true){
                    navController.navigateToTracksList()
                }
            }
        )
        searchScreen()
        favoritesScreen()
        tracksListScreen(
            onNavBack = {
                if (navController.currentBackStackEntry?.lifecycleIsResumed() == true){
                    navController.popBackStack()
                }
            }
        )

    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED