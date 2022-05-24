package com.example.ryckandmorty.data.remote

import javax.inject.Inject

class CharactersService @Inject constructor(private val charactersApi: CharactersApi) {

    suspend fun getCharacters(page: Int) = charactersApi.getCharacters(page)
}