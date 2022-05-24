package com.example.ryckandmorty.data.remote

import com.example.ryckandmorty.data.model.*
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class CharactersServiceTest {
    @RelaxedMockK
    private lateinit var charactersService: CharactersService

    private lateinit var charactersResponse: CharactersResponse

    @BeforeAll
    fun setUp() {
        charactersResponse = CharactersResponse(
            CharactersInfo("1", "2"), listOf(
                Characters(
                    2,
                    "Morty Smith",
                    "Alive",
                    "Human",
                    "Male",
                    Origin("Earth C-137"),
                    Location("Citadel of Rick's"),
                    "photo"
                ),
                Characters(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "Human",
                    "Male",
                    Origin("Earth C-137"),
                    Location("Citadel of Rick's"),
                    "photo"
                )
            )
        )
    }

    @Test
    fun fetchCharactersTest() = runBlocking {
        coEvery {
            charactersService.getCharacters(1)
        } returns Response.success(charactersResponse)

        assertEquals(charactersResponse, charactersService.getCharacters(1).body())
    }
}