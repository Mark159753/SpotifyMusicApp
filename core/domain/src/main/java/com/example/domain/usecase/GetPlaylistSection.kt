package com.example.domain.usecase

import androidx.annotation.StringRes
import com.example.common.PlaylistCategory
import com.example.domain.R

class GetPlaylistSection {

    operator fun invoke() = listOf(
        PlayListSection(
            title = R.string.top_play_lists_title,
            category = PlaylistCategory.TopLists
        ),
        PlayListSection(
            title = R.string.mood_lists_title,
            category = PlaylistCategory.Mood
        ),
        PlayListSection(
            title = R.string.rock_lists_title,
            category = PlaylistCategory.Rock
        ),
        PlayListSection(
            title = R.string.workout_lists_title,
            category = PlaylistCategory.Workout
        ),
        PlayListSection(
            title = R.string.gaming_lists_title,
            category = PlaylistCategory.Gaming
        ),
    )
}

data class PlayListSection(
    @StringRes
    val title:Int,
    val category: PlaylistCategory
)