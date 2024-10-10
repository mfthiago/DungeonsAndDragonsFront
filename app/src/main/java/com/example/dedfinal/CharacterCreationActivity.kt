package com.example.dedfinal

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.dedfinal.databinding.CharacterCreationBinding

class CharacterCreationActivity : AppCompatActivity() {

    private lateinit var binding: CharacterCreationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val race = intent.getStringExtra("selected_race")
        binding.txtRaceTitle.text = race

        // Set the attributes based on the selected race
        // Example:
        if (race == "Anão") {
             binding.forca_valor.setText("12")
         }

        setupIncrementDecrementButtons()
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
            editText.setText((currentValue + 1).toString())
        }

        subButton.setOnClickListener {
            val currentValue = editText.text.toString().toInt()
            if (currentValue > 0) {
                editText.setText((currentValue - 1).toString())
            }
        }
    }
}