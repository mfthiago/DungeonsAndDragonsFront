package com.example.dedfinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishedCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInicioButton()

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
                "Anão" -> Anao()
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
    private fun setupInicioButton() {
        val inicioButton: Button = findViewById(R.id.inicio_button)
        inicioButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}