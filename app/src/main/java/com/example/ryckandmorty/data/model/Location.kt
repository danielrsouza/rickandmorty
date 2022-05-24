package com.example.ryckandmorty.data.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String,
)
