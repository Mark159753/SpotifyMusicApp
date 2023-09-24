package com.example.data.mappers.playlists

import com.example.common.PlaylistCategory
import com.example.data.models.playlist.PlaylistModel
import com.example.localdata.db.entities.PlaylistEntity
import com.example.network.models.playlist.PlaylistItem

fun PlaylistEntity.toModel() = PlaylistModel(
    id = id,
    name = name,
    description = description,
    image = image,
    type = type,
    category = category
)

fun PlaylistItem.toEntity(category: PlaylistCategory = PlaylistCategory.None) = PlaylistEntity(
    id = id,
    name = name,
    description = description,
    image = images.maxByOrNull { it.width ?: 0 }?.url ?: "",
    type = type,
    category = category
)