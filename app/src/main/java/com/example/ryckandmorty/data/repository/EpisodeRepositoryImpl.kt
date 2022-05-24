package com.example.ryckandmorty.data.repository

import EpisodePagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.data.remote.EpisodeService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeService
) : EpisodeRepository {

    override fun getEpisodes(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { EpisodePagingSource(service = service) }
        ).flow
    }

}