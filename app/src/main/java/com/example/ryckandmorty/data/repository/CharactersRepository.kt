package com.example.ryckandmorty.data.repository

import androidx.paging.PagingData
import com.example.ryckandmorty.data.model.Characters
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<PagingData<Characters>>
}