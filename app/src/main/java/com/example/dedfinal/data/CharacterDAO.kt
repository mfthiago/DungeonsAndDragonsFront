package com.example.dedfinal.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dedfinal.data.entities.CharacterEntity

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): LiveData<CharacterEntity>

    @Insert
    fun insertCharacter(character: CharacterEntity)

    @Update
    fun updateCharacter(character: CharacterEntity)

    @Delete
    fun deleteCharacter(character: CharacterEntity)
}