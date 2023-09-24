package com.example.network.models.recomendations


import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(
    @SerializedName("seeds")
    val seeds: List<Seed>,
    @SerializedName("tracks")
    val tracks: List<Track>
)