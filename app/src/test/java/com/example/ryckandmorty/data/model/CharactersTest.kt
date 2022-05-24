package com.example.ryckandmorty.data.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CharactersTest {

    @Test
    fun createModelCharactersTest() {
        val target = Characters(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "Male",
            Origin("Earth C-137"),
            Location("Citadel of Rick's"),
            "photo"
        )

        assertNotNull(target)
        assertEquals(target.id, 1)
        assertEquals(target.name, "Rick Sanchez")
        assertEquals(target.status, "Alive")
        assertEquals(target.species, "Human")
        assertEquals(target.gender, "Male")
        assertEquals(target.name, "Rick Sanchez")
        assertNotNull(target.origin)
        assertEquals(target.origin.name, "Earth C-137")
        assertNotNull(target.location)
        assertEquals(target.location.name, "Citadel of Rick's")
        assertEquals(target.photo, "photo")
    }

    @Test
    fun createModelCharactersInfoTest() {
        val target = CharactersInfo("2", "1")

        assertNotNull(target)
        assertEquals(target.nextPage, "2")
        assertEquals(target.prevPage, "1")
    }

    @Test
    fun createModelCharactersResponseTest() {
        val characterOne = Characters(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "Male",
            Origin("Earth C-137"),
            Location("Citadel of Rick's"),
            "photo"
        )

        val characterTwo = Characters(
            2,
            "Morty Smith",
            "Alive",
            "Human",
            "Male",
            Origin("Earth C-137"),
            Location("Citadel of Rick's"),
            "photo"
        )

        val charactersInfo = CharactersInfo("2", "1")
        val charactersResult = listOf<Characters>(characterOne, characterTwo)
        val target = CharactersResponse(charactersInfo, charactersResult)

        assertNotNull(target)
        assertEquals(target.characters.size, 2)
        assertEquals(target.characters[0].name, "Rick Sanchez")
        assertEquals(target.characters[1].name, "Morty Smith")
    }

    @Test
    fun createModelOriginTest() {
        val target = Origin("Earth C-137")

        assertNotNull(target)
        assertEquals(target.name, "Earth C-137")
    }

    @Test
    fun createModelLocationTest() {
        val target = Location("Citadel of Rick's")

        assertNotNull(target)
        assertEquals(target.name, "Citadel of Rick's")
    }
}