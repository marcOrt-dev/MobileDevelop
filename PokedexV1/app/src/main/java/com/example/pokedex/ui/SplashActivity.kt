package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pokedex.R
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pokedex.service.MusicService


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_splash)

            val splashLayout = findViewById<ConstraintLayout>(R.id.rootLayout)
            val title = findViewById<TextView>(R.id.appTitle)
            val tapToContinue = findViewById<TextView>(R.id.tapToContinueText)
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
            tapToContinue.startAnimation(animation)

            val intent = Intent(this, MusicService::class.java)
            startService(intent)

            // Listener para cualquier parte de la pantalla
            splashLayout.setOnClickListener {
                startActivity(Intent(this, PokemonList::class.java))
                finish()
            }

            // (Opcional) También puedes dejar el click en el texto
            tapToContinue.setOnClickListener {
                startActivity(Intent(this, PokemonList::class.java))
                finish()
            }

        } catch (e: Exception) {
            Log.e("SplashActivity", "Error during splash setup", e)
            showError("Ocurrió un error al iniciar la aplicación. Intente nuevamente.")
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
