package com.example.dedfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.dedfinal.data.CharacterDB
import com.example.dedfinal.data.entities.CharacterEntity

class CharacterListActivity : AppCompatActivity() {

    private lateinit var characterDB: CharacterDB
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        characterDB = Room.databaseBuilder(
            applicationContext,
            CharacterDB::class.java, CharacterDB.NAME
        ).build()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CharacterAdapter { character ->
            deleteCharacter(character)
        }
        recyclerView.adapter = adapter

        characterDB.getCharacterDao().getAllCharacters().observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })

        val mainButton: Button = findViewById(R.id.back_to_main_button)
        mainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteCharacter(character: CharacterEntity) {
        Thread {
            characterDB.getCharacterDao().deleteCharacter(character)
        }.start()
    }
}