package com.example.data.mappers.albums

import com.example.data.models.albums.AlbumModel
import com.example.data.models.albums.Artist
import com.example.localdata.db.entities.RecommendedTracksEntity
import com.example.localdata.db.entities.ReleasesAlbumsEntity
import com.example.network.models.album.AlbumResponse
import com.example.network.models.albums.AlbumItem
import com.example.network.models.recomendations.Track

fun AlbumItem.toReleaseAlbumEntity() = ReleasesAlbumsEntity(
    id = id,
    name = name,
    image = images.maxByOrNull { it.width }?.url ?: "",
    type = type,
    releaseDate = releaseDate,
    totalTracks = totalTracks,
    artists = artists.map { it.toEntity() }
)

fun ReleasesAlbumsEntity.toModel() = AlbumModel(
    id = id,
    name = name,
    image = image,
    type = type,
    releaseDate = releaseDate,
    totalTracks = totalTracks,
    artists = artists.map { it.toModel() }
)

fun AlbumItem.toModel() = AlbumModel(
    id = id,
    name = name,
    image = images.maxByOrNull { it.width }?.url ?: "",
    type = type,
    releaseDate = releaseDate,
    totalTracks = totalTracks,
    artists = artists.map {
        com.example.data.models.albums.Artist(
            id = it.id,
            name = it.name,
            type = it.type
        )
    }
)

fun AlbumResponse.toModel() = AlbumModel(
    id = id,
    name = name,
    image = images.maxByOrNull { it.width }?.url ?: "",
    type = type,
    releaseDate = releaseDate,
    totalTracks = totalTracks,
    artists = artists.map {
        com.example.data.models.albums.Artist(
            id = it.id,
            name = it.name,
            type = it.type
        )
    }
)

fun com.example.network.models.albums.Artist.toEntity() = com.example.localdata.db.entities.Artist(
    id = id,
    name = name,
    type = type
)

fun com.example.localdata.db.entities.Artist.toModel() = com.example.data.models.albums.Artist(
    id = id,
    name = name,
    type = type
)

fun Track.toModel() = AlbumModel(
    id = album.id,
    name = album.name,
    image = album.images.maxByOrNull { it.width }?.url ?: "",
    type = album.type,
    releaseDate = album.releaseDate,
    totalTracks = album.totalTracks,
    artists = album.artists.map { artist ->
        Artist(
            id = artist.id,
            name = artist.name,
            type = artist.type
        )
    }
)

fun RecommendedTracksEntity.toModel() = AlbumModel(
    id = id,
    name = name,
    image = image,
    type = type,
    releaseDate = releaseDate,
    totalTracks = totalTracks,
    artists = artists.map { artist ->
        Artist(
            id = artist.id,
            name = artist.name,
            type = artist.type
        )
    }
)

fun Track.toEntity() = RecommendedTracksEntity(
    id = album.id,
    name = album.name,
    image = album.images.maxByOrNull { it.width }?.url ?: "",
    type = album.type,
    releaseDate = album.releaseDate,
    totalTracks = album.totalTracks,
    artists = album.artists.map { artist ->
        com.example.localdata.db.entities.Artist(
            id = artist.id,
            name = artist.name,
            type = artist.type
        )
    }
)