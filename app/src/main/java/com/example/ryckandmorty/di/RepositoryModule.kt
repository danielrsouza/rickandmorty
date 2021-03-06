package com.example.ryckandmorty.di

import com.example.ryckandmorty.data.repository.CharactersRepository
import com.example.ryckandmorty.data.repository.CharactersRepositoryImpl
import com.example.ryckandmorty.data.repository.EpisodeRepository
import com.example.ryckandmorty.data.repository.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCharacterRepository(
        characterRepositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository

    @Binds
    abstract fun provideEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodeRepository
}