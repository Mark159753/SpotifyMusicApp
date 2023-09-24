package com.example.network

import com.example.network.adapter.NetworkResponse
import com.example.network.models.album.AlbumResponse
import com.example.network.models.albums.AlbumsResponse
import com.example.network.models.albums_tract.AlbumsTracksResponse
import com.example.network.models.error.ErrorResponse
import com.example.network.models.playlist.PlaylistsResponse
import com.example.network.models.recomendations.RecommendationsResponse
import com.example.network.models.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

typealias GenericResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {

    @GET("albums/{id}")
    suspend fun getAlbum(
        @Path("id") id:String,
        @Query("market") market:String? = null
    ):GenericResponse<AlbumResponse>

    @GET("albums/{id}/tracks")
    suspend fun getAlbumsTracts(
        @Path("id") id:String,
        @Query("limit") limit:Int = 16,
        @Query("offset") offset:Int = 0,
    ):GenericResponse<AlbumsTracksResponse>

    @GET("browse/new-releases")
    suspend fun getNewAlbumsReleases(
        @Query("limit") limit:Int = 16,
        @Query("offset") offset:Int = 0,
        @Query("country") country:String? = null,
    ):GenericResponse<AlbumsResponse>

    @GET("me")
    suspend fun getCurrentUser():GenericResponse<UserResponse>

    @GET("recommendations")
    suspend fun getRecommendations(
        @Query("limit") limit:Int,
        @Query("seed_genres") seedGenres:String
    ):GenericResponse<RecommendationsResponse>

    @GET("browse/categories/{category_id}/playlists")
    suspend fun getPlaylistByCategory(
        @Path("category_id") categoryId:String,
        @Query("limit") limit:Int = 16,
        @Query("offset") offset:Int = 0,
    ):GenericResponse<PlaylistsResponse>
}