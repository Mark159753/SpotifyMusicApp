package com.example.spotifymusicapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.favorites.navigation.FavoritesNavigationRoute
import com.example.favorites.navigation.navigateToFavorites
import com.example.home.navigation.HomeNavigationRoute
import com.example.home.navigation.navigateToHome
import com.example.search.navigation.SearchNavigationRoute
import com.example.search.navigation.navigateToSearch
import com.example.spotifymusicapp.navigation.BottomBarDestinations

@Composable
fun rememberSpotifyAppState(
    navController: NavHostController = rememberNavController(),
): SpotifyAppState {
    return remember(
        navController
    ) {
        SpotifyAppState(
            navController,
        )
    }
}

@Stable
class SpotifyAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentBottomBarDestination: BottomBarDestinations?
        @Composable get() = when (currentDestination?.route) {
            HomeNavigationRoute -> BottomBarDestinations.HOME
            SearchNavigationRoute -> BottomBarDestinations.SEARCH
            FavoritesNavigationRoute -> BottomBarDestinations.FAVORITES
            else -> null
        }

    val bottomBarDestinations: List<BottomBarDestinations> = BottomBarDestinations
        .values().asList()

    fun navigateToBottomBarDestination(bottomBarDestination: BottomBarDestinations) {
        val navOps = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when(bottomBarDestination){
            BottomBarDestinations.HOME -> navController.navigateToHome(navOps)
            BottomBarDestinations.SEARCH -> navController.navigateToSearch(navOps)
            BottomBarDestinations.FAVORITES -> navController.navigateToFavorites(navOps)
        }
    }
}