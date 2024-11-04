package com.example.dedfinal

import android.os.Bundle
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
        adapter = CharacterAdapter()
        recyclerView.adapter = adapter

        characterDB.getCharacterDao().getAllCharacters().observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })
    }
}