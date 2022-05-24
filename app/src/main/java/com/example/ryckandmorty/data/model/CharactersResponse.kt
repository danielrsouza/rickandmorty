package com.example.ryckandmorty.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: CharactersInfo,
    @SerializedName("results")
    val characters: List<Characters>
)