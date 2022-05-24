package com.example.ryckandmorty.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.data.repository.EpisodeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepositoryImpl
) : ViewModel() {

    fun getEpisodes(): Flow<PagingData<Episode>> {
        return episodeRepository.getEpisodes().cachedIn(viewModelScope)
    }
}