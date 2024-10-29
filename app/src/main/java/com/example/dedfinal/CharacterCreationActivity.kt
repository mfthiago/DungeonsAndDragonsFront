package com.example.dedfinal

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            } else {

                showCustomNotification(this)
            }

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

    private fun showCustomNotification(context: Context) {
        val channelId = "movie_night_channel"
        val notificationId = 101


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Movie Night Channel"
            val descriptionText = "Channel for movie night notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_maisum)
            .setContentTitle("Justin Rhyss")
            .setContentText("Hey, do you have any plans for tonight? I was thinking a few of us could go watch a movie...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Hey, do you have any plans for tonight? I was thinking a few of us could go watch a movie at the theater nearby since there won’t be much going on for the next couple of weeks. There are some great options at 6 and 7pm, but whatever works best for you. If you have any suggestions for dinner beforehand hit reply!")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_menosum, "Reply", null)  // Reply action (can add Intent)
            .addAction(R.drawable.ic_maisum, "Archive", null)  // Archive action (can add Intent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as AppCompatActivity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
            notify(notificationId, notificationBuilder.build())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                showCustomNotification(this)
            } else {

            }
        }
    }
}