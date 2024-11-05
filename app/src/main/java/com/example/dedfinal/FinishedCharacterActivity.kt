package com.example.dedfinal


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.dedfinal.data.CharacterDB
import com.example.dedfinal.data.entities.CharacterEntity

import com.example.dedfinal.databinding.FinishedCharacterBinding
import models.Anao
import models.Elfo
import models.Gnomo
import models.Humano
import models.MeioElfo
import models.Orc
import models.Personagem
import services.RacaStrategy

class FinishedCharacterActivity : AppCompatActivity() {

    private lateinit var binding: FinishedCharacterBinding
    private lateinit var characterDB: CharacterDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishedCharacterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        characterDB = Room.databaseBuilder(
            applicationContext,
            CharacterDB::class.java, "character_db"
        ).build()

        val sharedPreferences = getSharedPreferences("com.example.dedfinal.PREFERENCES", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("saved_name", "Nome não encontrado") ?: "Nome não encontrado"
        val selectedRace = sharedPreferences.getString("selected_race", "Raça não encontrada") ?: "Raça não encontrada"
        val forca = sharedPreferences.getInt("forca", 0)
        val destreza = sharedPreferences.getInt("destreza", 0)
        val constituicao = sharedPreferences.getInt("constituicao", 0)
        val inteligencia = sharedPreferences.getInt("inteligencia", 0)
        val sabedoria = sharedPreferences.getInt("sabedoria", 0)
        val carisma = sharedPreferences.getInt("carisma", 0)





        fun getRacaStrategy(selectedRace: String): RacaStrategy {
            return when (selectedRace) {
                "Elfo" -> Elfo()
                "Humano" -> Humano()
                "Anao" -> Anao()
                "Orc" -> Orc()
                "Gnomo" -> Gnomo()
                "Meio-Elfo" -> MeioElfo()
                else -> throw IllegalArgumentException("Raça desconhecida: $selectedRace")
            }
        }
        val raca = getRacaStrategy(selectedRace)
        val personagem = Personagem(
            nome = savedName,
            raca = raca,
            forca = forca,
            destreza = destreza,
            constituicao = constituicao,
            inteligencia = inteligencia,
            sabedoria = sabedoria,
            carisma = carisma,
        )
        personagem.aplicarBonusRacial()

        displayCharacterInfo(personagem)
        setupListaButton(personagem)
    }
    @SuppressLint("LongLogTag")
    private fun saveCharacterToDB(personagem: Personagem) {
        val characterEntity = CharacterEntity(
            nome = personagem.nome,
            raca = personagem.raca.javaClass.simpleName,
            forca = personagem.forca,
            destreza = personagem.destreza,
            constituicao = personagem.constituicao,
            inteligencia = personagem.inteligencia,
            sabedoria = personagem.sabedoria,
            carisma = personagem.carisma
        )

        Thread {
            try {
                Log.d("FinishedCharacterActivity", "Inserting character: $characterEntity")
                characterDB.getCharacterDao().insertCharacter(characterEntity)
                Log.d("FinishedCharacterActivity", "Character inserted successfully")
            } catch (e: Exception) {
                Log.e("FinishedCharacterActivity", "Error inserting character", e)
            }
        }.start()
    }


    private fun displayCharacterInfo(personagem: Personagem) {
        binding.nameTextView.text = "Nome: ${personagem.nome}"
        binding.raceTextView.text = "Raça: ${personagem.raca.toString()}"
        binding.attributesTextView.text = """
            Atributos:
            Força: ${personagem.forca}
            Destreza: ${personagem.destreza}
            Constituição: ${personagem.constituicao}
            Inteligência: ${personagem.inteligencia}
            Sabedoria: ${personagem.sabedoria}
            Carisma: ${personagem.carisma}
        """.trimIndent()
        binding.hitPointsTextView.text = "Pontos de Vida: ${personagem.pontosDeVida}"
    }
    @SuppressLint("LongLogTag")
    private fun setupListaButton(personagem: Personagem) {
        val listaButton: Button = findViewById(R.id.lista_button)
        listaButton.setOnClickListener {
            try {
                saveCharacterToDB(personagem)
                val intent = Intent(this, CharacterListActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Log.e("FinishedCharacterActivity", "Error in setupListaButton", e)
            }
        }
    }
}