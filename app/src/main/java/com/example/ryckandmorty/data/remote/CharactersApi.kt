package com.example.ryckandmorty.data.remote

import com.example.ryckandmorty.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharactersResponse>
}