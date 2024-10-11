package com.example.dedfinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dedfinal.databinding.CharacterCreationBinding
import services.DistribuicaoAtributos


class CharacterCreationActivity : AppCompatActivity() {

    private lateinit var binding: CharacterCreationBinding
    private var totalPoints = 27

    val custoPontos = DistribuicaoAtributos().custoPontos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences =
            getSharedPreferences("com.example.dedfinal.PREFERENCES", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("saved_name", "Nome não encontrado")
        val selectedRace = sharedPreferences.getString("selected_race", "Raça não encontrada")
        binding.nameTextView.text = savedName
        binding.raceTextView.text = selectedRace

        mostrarBonusRacial(selectedRace)
        setupIncrementDecrementButtons()
        setupBackButton()
        setupContinuarButton()
        updatePointsRemaining()

    }



    private fun setupIncrementDecrementButtons() {
        setupButton(binding.btAddForca, binding.btSubForca, binding.forcaValor)
        setupButton(binding.btAddDestreza, binding.btSubDestreza, binding.destrezaValor)
        setupButton(binding.btAddConstituicao, binding.btSubConstituicao, binding.constituicaoValor)
        setupButton(binding.btAddSabedoria, binding.btSubSabedoria, binding.sabedoriaValor)
        setupButton(binding.btAddInteligencia, binding.btSubInteligencia, binding.inteligenciaValor)
        setupButton(binding.btAddCarisma, binding.btSubCarisma, binding.carismaValor)
    }

    private fun setupButton(addButton: ImageButton, subButton: ImageButton, editText: EditText) {
        addButton.setOnClickListener {
            val currentValue = editText.text.toString().toInt()
            val nextValue = currentValue + 1
            val currentCost = custoPontos[currentValue] ?: 0
            val nextCost = custoPontos[nextValue] ?: 0
            val costDifference = nextCost - currentCost

            if (nextValue <= 15 && totalPoints >= costDifference) {
                editText.setText(nextValue.toString())
                totalPoints -= costDifference
                updatePointsRemaining()
            }
        }

        subButton.setOnClickListener {
            val currentValue = editText.text.toString().toInt()
            val prevValue = currentValue - 1
            val currentCost = custoPontos[currentValue] ?: 0
            val prevCost = custoPontos[prevValue] ?: 0
            val costDifference = currentCost - prevCost

            if (prevValue >= 8) {
                editText.setText(prevValue.toString())
                totalPoints += costDifference
                updatePointsRemaining()
            }
        }
    }

    private fun setupBackButton() {
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
    private fun setupContinuarButton() {
        val continueButton: Button = findViewById(R.id.continue_button)
        continueButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("com.example.dedfinal.PREFERENCES", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putInt("forca", binding.forcaValor.text.toString().toInt())
            editor.putInt("destreza", binding.destrezaValor.text.toString().toInt())
            editor.putInt("constituicao", binding.constituicaoValor.text.toString().toInt())
            editor.putInt("inteligencia", binding.inteligenciaValor.text.toString().toInt())
            editor.putInt("sabedoria", binding.sabedoriaValor.text.toString().toInt())
            editor.putInt("carisma", binding.carismaValor.text.toString().toInt())


            editor.apply()

            val intent = Intent(this, FinishedCharacterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updatePointsRemaining() {
        val pointsRemainingTextView: TextView = findViewById(R.id.points_remaining)
        pointsRemainingTextView.text = "Pontos restantes: $totalPoints"
    }

    private fun mostrarBonusRacial(raca: String?) {
        val forcaBonusTextView: TextView = findViewById(R.id.forca_bonus)
        val destrezaBonusTextView: TextView = findViewById(R.id.destreza_bonus)
        val constituicaoBonusTextView: TextView = findViewById(R.id.constituicao_bonus)
        val inteligenciaBonusTextView: TextView = findViewById(R.id.inteligencia_bonus)
        val sabedoriaBonusTextView: TextView = findViewById(R.id.sabedoria_bonus)
        val carismaBonusTextView: TextView = findViewById(R.id.carisma_bonus)

        when (raca) {
            "Elfo" -> destrezaBonusTextView.text = "+2"
            "Humano" -> {
                forcaBonusTextView.text = "+1"
                destrezaBonusTextView.text = "+1"
                constituicaoBonusTextView.text = "+1"
                inteligenciaBonusTextView.text = "+1"
                sabedoriaBonusTextView.text = "+1"
                carismaBonusTextView.text = "+1"
            }

            "Anão" -> constituicaoBonusTextView.text = "+2"
            "Orc" -> {
                forcaBonusTextView.text = "+2"
                constituicaoBonusTextView.text = "+1"
            }

            "Gnomo" -> inteligenciaBonusTextView.text = "+2"
            "Meio-Elfo" -> carismaBonusTextView.text = "+2"
        }
    }
}