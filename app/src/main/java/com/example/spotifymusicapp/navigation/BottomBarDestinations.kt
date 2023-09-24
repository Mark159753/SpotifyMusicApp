package com.example.spotifymusicapp.navigation

import com.example.ui.R

enum class BottomBarDestinations(
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val iconTextId: Int,
    val titleTextId: Int,
){
    HOME(
        selectedIconId = R.drawable.filled_home_24,
        unselectedIconId = R.drawable.outline_home,
        iconTextId = com.example.spotifymusicapp.R.string.bottom_bar_home,
        titleTextId = com.example.spotifymusicapp.R.string.bottom_bar_home
    ),

    SEARCH(
        selectedIconId = R.drawable.search_24,
        unselectedIconId = R.drawable.search_24,
        iconTextId = com.example.spotifymusicapp.R.string.bottom_bar_search,
        titleTextId = com.example.spotifymusicapp.R.string.bottom_bar_search
    ),

    FAVORITES(
        selectedIconId = R.drawable.favorite_24,
        unselectedIconId = R.drawable.favorite_border_24,
        iconTextId = com.example.spotifymusicapp.R.string.bottom_bar_favorites,
        titleTextId = com.example.spotifymusicapp.R.string.bottom_bar_favorites
    )

}