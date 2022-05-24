package com.example.ryckandmorty.data.remote

import com.example.ryckandmorty.data.model.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
    @GET("api/episode/")
    suspend fun getEpisodes(@Query("page") page: Int): Response<EpisodeResponse>
}