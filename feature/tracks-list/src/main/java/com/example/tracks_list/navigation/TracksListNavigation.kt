package com.example.tracks_list.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.tracks_list.ui.TracksListsRoute

const val TracksListNavigationRoute = "tracks_list_route"

fun NavController.navigateToTracksList(navOptions: NavOptions? = null) {
    this.navigate(TracksListNavigationRoute, navOptions)
}

fun NavGraphBuilder.tracksListScreen(
    onNavBack:()->Unit,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = enterTransition,
    popExitTransition: (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? = exitTransition,
) {
    composable(
        route = TracksListNavigationRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popExitTransition = popExitTransition,
        popEnterTransition = popEnterTransition
    ) {
        TracksListsRoute(
            onNavBack = onNavBack
        )
    }
}