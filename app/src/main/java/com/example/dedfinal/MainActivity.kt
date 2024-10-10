package com.example.dedfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dedfinal.adapter.AtributoAdapter
import com.example.dedfinal.databinding.ActivityMainBinding
import com.example.dedfinal.listitems.AtributosList
import com.example.dedfinal.model.Atributos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var atributoAdapter: AtributoAdapter
    private val atributos = AtributosList()
    private val atributosList : MutableList<Atributos> = mutableListOf()
    var clicked = false


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")



        val recyclerViewAtributos = binding.recyclerViewAtributos
        recyclerViewAtributos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewAtributos.setHasFixedSize(true)
        atributoAdapter = AtributoAdapter(this,atributosList)
        recyclerViewAtributos.adapter = atributoAdapter


        binding.anao.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            startActivity(intent)
        }


        binding.elfo.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            intent.putExtra("selected_race", "Elfo")
            startActivity(intent)
        }
        binding.orc.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            intent.putExtra("selected_race", "Orc")
            startActivity(intent)
        }

        binding.meioelfo.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            intent.putExtra("selected_race", "Meio-Elfo")
            startActivity(intent)
        }
        binding.humano.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            intent.putExtra("selected_race", "Humano")
            startActivity(intent)
        }
        binding.gnomo.setOnClickListener {
            val intent = Intent(this, CharacterCreationActivity::class.java)
            intent.putExtra("selected_race", "Gnomo")
            startActivity(intent)
        }



        binding.elfo.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.anao.setTextColor(R.color.dark_gray)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.elfo.setTextColor(Color.WHITE)
            binding.humano.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.humano.setTextColor(R.color.dark_gray)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.gnomo.setTextColor(R.color.dark_gray)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.meioelfo.setTextColor(R.color.dark_gray)
            binding.orc.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.orc.setTextColor(R.color.dark_gray)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "elfo"


        }

        binding.humano.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.anao.setTextColor(R.color.dark_gray)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.elfo.setTextColor(R.color.dark_gray)
            binding.humano.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.humano.setTextColor(Color.WHITE)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.gnomo.setTextColor(R.color.dark_gray)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.meioelfo.setTextColor(R.color.dark_gray)
            binding.orc.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.orc.setTextColor(R.color.dark_gray)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "humano"


        }

        binding.gnomo.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.anao.setTextColor(R.color.dark_gray)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.elfo.setTextColor(R.color.dark_gray)
            binding.humano.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.humano.setTextColor(R.color.dark_gray)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.gnomo.setTextColor(Color.WHITE)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.meioelfo.setTextColor(R.color.dark_gray)
            binding.orc.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.orc.setTextColor(R.color.dark_gray)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "gnomo"


        }

        binding.meioelfo.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.anao.setTextColor(R.color.dark_gray)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.elfo.setTextColor(R.color.dark_gray)
            binding.humano.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.humano.setTextColor(R.color.dark_gray)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.gnomo.setTextColor(R.color.dark_gray)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.meioelfo.setTextColor(Color.WHITE)
            binding.orc.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.orc.setTextColor(R.color.dark_gray)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "meioelfo"


        }


        binding.orc.setOnClickListener {
            clicked = true
            binding.anao.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.anao.setTextColor(R.color.dark_gray)
            binding.elfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.elfo.setTextColor(R.color.dark_gray)
            binding.humano.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.humano.setTextColor(R.color.dark_gray)
            binding.gnomo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.gnomo.setTextColor(R.color.dark_gray)
            binding.meioelfo.setBackgroundResource(R.drawable.bg_button_disabled)
            binding.meioelfo.setTextColor(R.color.dark_gray)
            binding.orc.setBackgroundResource(R.drawable.bg_button_enabled)
            binding.orc.setTextColor(Color.WHITE)
            binding.recyclerViewAtributos.visibility = View.INVISIBLE
            binding.txtListTitle.text = "orc"


        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}