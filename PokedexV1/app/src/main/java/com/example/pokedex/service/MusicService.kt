package com.example.pokedex.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.pokedex.R

class MusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.opening).apply {
            isLooping = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer?.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // Aquí puedes pausar la música si es necesario
    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    // Aquí puedes reanudar la música
    fun resumeMusic() {
        mediaPlayer?.start()
    }
}
