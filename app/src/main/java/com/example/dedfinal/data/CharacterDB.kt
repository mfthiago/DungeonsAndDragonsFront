package com.example.dedfinal.data

import com.example.dedfinal.data.entities.CharacterEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDB : RoomDatabase() {
    companion object {
        const val NAME = "character_db"
    }

    abstract fun getCharacterDao(): CharacterDAO
}