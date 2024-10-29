import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dedfinal.CharacterCreationActivity
import com.example.dedfinal.R
import com.example.dedfinal.adapter.AtributoAdapter
import com.example.dedfinal.databinding.ActivityMainBinding
import com.example.dedfinal.listitems.AtributosList
import com.example.dedfinal.model.Atributos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import models.*
import services.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var atributoAdapter: AtributoAdapter
    private val atributos = AtributosList()
    private var selectedRace: RacaStrategy? = null
    private val atributosList: MutableList<Atributos> = mutableListOf()
    var clicked = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveNameButton.setOnClickListener {
            val nameInput: EditText = findViewById(R.id.name_input)
            val name = nameInput.text.toString()
            continuar(name)
        }

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val recyclerViewAtributos = binding.recyclerViewAtributos
        recyclerViewAtributos.layoutManager = LinearLayoutManager(this)
        recyclerViewAtributos.setHasFixedSize(true)
        atributoAdapter = AtributoAdapter(this, atributosList)
        recyclerViewAtributos.adapter = atributoAdapter

        binding.anao.setOnClickListener {
            clicked = true
            selectedRace = Anao()
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
        }

        binding.elfo.setOnClickListener {
            clicked = true
            selectedRace = Elfo()
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
        }

        binding.humano.setOnClickListener {
            clicked = true
            selectedRace = Humano()
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
        }

        binding.gnomo.setOnClickListener {
            clicked = true
            selectedRace = Gnomo()
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
        }

        binding.meioelfo.setOnClickListener {
            clicked = true
            selectedRace = MeioElfo()
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
        }

        binding.orc.setOnClickListener {
            clicked = true
            selectedRace = Orc()
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
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun continuar(name: String) {
        val sharedPreferences = getSharedPreferences("com.example.dedfinal.PREFERENCES", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("saved_name", name)
        editor.putString("selected_race", selectedRace?.javaClass?.simpleName)
        editor.apply()


        val intent = Intent(this, CharacterCreationActivity::class.java)
        startActivity(intent)
    }


}