package com.example.dedfinal

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dedfinal.databinding.FinishedCharacterBinding

class FinishedCharacterActivity : AppCompatActivity() {

    private lateinit var binding: FinishedCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishedCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("com.example.dedfinal.PREFERENCES", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("saved_name", "Nome não encontrado")
        val selectedRace = sharedPreferences.getString("selected_race", "Raça não encontrada")
        val forca = sharedPreferences.getInt("forca", 0)
        val destreza = sharedPreferences.getInt("destreza", 0)
        val constituicao = sharedPreferences.getInt("constituicao", 0)
        val inteligencia = sharedPreferences.getInt("inteligencia", 0)
        val sabedoria = sharedPreferences.getInt("sabedoria", 0)
        val carisma = sharedPreferences.getInt("carisma", 0)
        val hitPoints = 10 + constituicao

        binding.nameTextView.text = "Nome: $savedName"
        binding.raceTextView.text = "Raça: $selectedRace"
        binding.attributesTextView.text = """
            Atributos:
            Força: $forca
            Destreza: $destreza
            Constituição: $constituicao
            Inteligência: $inteligencia
            Sabedoria: $sabedoria
            Carisma: $carisma
        """.trimIndent()
        binding.hitPointsTextView.text = "Pontos de Vida: $hitPoints"
    }
}