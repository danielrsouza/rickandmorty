package com.example.ryckandmorty.data.repository

import CharactersPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.data.remote.CharactersService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersService
) : CharactersRepository {

    override fun getAllCharacters(): Flow<PagingData<Characters>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingSource(service = service) }
        ).flow
    }

}