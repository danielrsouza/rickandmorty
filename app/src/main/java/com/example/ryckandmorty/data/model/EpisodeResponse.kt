package com.example.ryckandmorty.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("info")
    val info: EpisodeInfo,
    @SerializedName("results")
    val episode: List<Episode>
)