package com.example.ryckandmorty.viewmodels

import EpisodePagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.data.model.EpisodeInfo
import com.example.ryckandmorty.data.model.EpisodeResponse
import com.example.ryckandmorty.data.remote.EpisodeService
import com.example.ryckandmorty.data.repository.EpisodeRepositoryImpl
import com.example.ryckandmorty.ui.viewmodels.EpisodeViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class EpisodeViewModelTest {

    @RelaxedMockK
    private lateinit var episodeService: EpisodeService

    @RelaxedMockK
    private lateinit var episodeRepository: EpisodeRepositoryImpl

    private lateinit var target: EpisodeViewModel

    private lateinit var episodeResponse: EpisodeResponse

    @BeforeAll
    fun setUp() {

        episodeResponse = EpisodeResponse(
            EpisodeInfo("1", "2"), listOf(
                Episode(
                    1,
                    "The Wedding Squanchers",
                    "October 4, 2015",
                    "S02E10",
                ),
                Episode(
                    1,
                    "The Rickshank Rickdemption",
                    "April 1, 2017",
                    "S03E01",
                )
            )
        )
        target = EpisodeViewModel(episodeRepository)
    }

    @Test
    fun getEpisodesTest() {
        coEvery {
            episodeService.getEpisodes(1)
        } returns Response.success(episodeResponse)

        coEvery {
            episodeRepository.getEpisodes()
        } returns Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { EpisodePagingSource(service = episodeService) }
        ).flow

        val result = target.getEpisodes()
        assertNotNull(result)
    }
}


