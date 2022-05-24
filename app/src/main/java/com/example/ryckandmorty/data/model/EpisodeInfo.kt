package com.example.ryckandmorty.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeInfo(
    @SerializedName("next")
    var nextPage: String,
    @SerializedName("prev")
    var prevPage: String,
)