package com.example.ryckandmorty.data.repository

import androidx.paging.PagingData
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.data.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodes(): Flow<PagingData<Episode>>
}