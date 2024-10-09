package com.example.dedfinal

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dedfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var clicked = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.anao.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.anao.setTextColor(Color.WHITE)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.elfo.setTextColor(R.color.dark_gray)
            binding.humano.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.humano.setTextColor(R.color.dark_gray)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.gnomo.setTextColor(R.color.dark_gray)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.meioelfo.setTextColor(R.color.dark_gray)
            binding.orc.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.orc.setTextColor(R.color.dark_gray)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "all"




        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}