package com.example.ryckandmorty.data.remote

import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.data.model.EpisodeInfo
import com.example.ryckandmorty.data.model.EpisodeResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class EpisodeServiceTest {
    @RelaxedMockK
    private lateinit var episodeService: EpisodeService

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
    }

    @Test
    fun getEpisodesTest() = runBlocking {
        coEvery {
            episodeService.getEpisodes(1)
        } returns Response.success(episodeResponse)

        assertEquals(episodeResponse, episodeService.getEpisodes(1).body())
    }
}