package com.example.ryckandmorty.viewmodels

import CharactersPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ryckandmorty.data.model.*
import com.example.ryckandmorty.data.remote.CharactersService
import com.example.ryckandmorty.data.repository.CharactersRepositoryImpl
import com.example.ryckandmorty.ui.viewmodels.CharactersViewModel
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
class CharactersViewModelTest {


    @RelaxedMockK
    private lateinit var charactersService: CharactersService

    @RelaxedMockK
    private lateinit var charactersRepository: CharactersRepositoryImpl

    private lateinit var target: CharactersViewModel

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
        target = CharactersViewModel(charactersRepository)
    }

    @Test
    fun fetchCharactersTest() {
        coEvery {
            charactersService.getCharacters(1)
        } returns Response.success(charactersResponse)

        coEvery {
            charactersRepository.getAllCharacters()
        } returns Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingSource(service = charactersService) }
        ).flow

        val result = target.fetchCharacters()
        assertNotNull(result)
    }
}


