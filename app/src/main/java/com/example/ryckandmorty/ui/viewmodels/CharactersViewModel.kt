package com.example.ryckandmorty.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.data.repository.CharactersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepositoryImpl
) : ViewModel() {

    fun fetchCharacters(): Flow<PagingData<Characters>> {
        return charactersRepository.getAllCharacters().cachedIn(viewModelScope)
    }
}
