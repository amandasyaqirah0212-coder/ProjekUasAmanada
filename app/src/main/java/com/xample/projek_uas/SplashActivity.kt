package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // --- TAMBAHAN KODE ANIMASI DI SINI ---
        // 1. Cari ImageView logo kamu
        val logo = findViewById<ImageView>(R.id.logoSplash)

        // 2. Ambil resep animasi goyang dari folder res/anim
        val goyang = AnimationUtils.loadAnimation(this, R.anim.goyang_logo)

        // 3. Jalankan animasinya!
        logo.startAnimation(goyang)
        // -------------------------------------

        // Delay selama 2 detik (300 milidetik)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}