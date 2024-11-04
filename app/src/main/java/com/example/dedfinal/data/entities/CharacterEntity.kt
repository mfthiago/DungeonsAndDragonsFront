package com.example.dedfinal.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val raca: String,
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int
)