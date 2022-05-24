package com.example.ryckandmorty.data.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EpisodeTest {
    @Test
    fun createModelEpisodeTest() {
        val target = Episode(
            1,
            "The Wedding Squanchers",
            "October 4, 2015",
            "S02E10",
        )

        Assertions.assertNotNull(target)
        Assertions.assertEquals(target.id, 1)
        Assertions.assertEquals(target.name, "The Wedding Squanchers")
        Assertions.assertEquals(target.airDate, "October 4, 2015")
        Assertions.assertEquals(target.episode, "S02E10")
    }

    @Test
    fun createModelEpisodeInfoTest() {
        val target = EpisodeInfo("2", "1")

        Assertions.assertNotNull(target)
        Assertions.assertEquals(target.nextPage, "2")
        Assertions.assertEquals(target.prevPage, "1")
    }

    @Test
    fun createModelEpisodeResponseTest() {
        val episodeOne = Episode(
            1,
            "The Wedding Squanchers",
            "October 4, 2015",
            "S02E10",
        )

        val episodeTwo = Episode(
            1,
            "The Rickshank Rickdemption",
            "April 1, 2017",
            "S03E01",
        )

        val episodeInfo = EpisodeInfo("2", "1")
        val episodeResult = listOf(episodeOne, episodeTwo)
        val target = EpisodeResponse(episodeInfo, episodeResult)

        Assertions.assertNotNull(target)
        Assertions.assertEquals(target.episode.size, 2)
        Assertions.assertEquals(target.episode[0].name, "The Wedding Squanchers")
        Assertions.assertEquals(target.episode[1].name, "The Rickshank Rickdemption")
    }
}