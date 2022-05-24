package com.example.ryckandmorty.data.remote

import javax.inject.Inject

class EpisodeService @Inject constructor(private val episodeApi: EpisodeApi) {

    suspend fun getEpisodes(page: Int) = episodeApi.getEpisodes(page)
}