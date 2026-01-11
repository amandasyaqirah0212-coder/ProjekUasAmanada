package com.xample.projek_uas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class PusatBantuanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pusat_bantuan)

        // 1. Inisialisasi View berdasarkan ID di XML kamu
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val menuChatCS = findViewById<CardView>(R.id.menuChatCS)

        // 2. Fungsi Tombol Kembali
        btnBack.setOnClickListener {
            finish() // Menutup halaman ini dan kembali ke profil
        }

        // 3. Fungsi Klik Chat CS (Membuka WhatsApp)
        menuChatCS.setOnClickListener {
            val nomorWA = "628123456789" // Ganti dengan nomor asli kamu
            val pesan = "Halo Admin, saya ingin bertanya tentang pesanan saya."

            try {
                // Mencoba membuka WhatsApp
                val url = "https://api.whatsapp.com/send?phone=$nomorWA&text=${Uri.encode(pesan)}"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: Exception) {
                // Jika WhatsApp tidak terinstall
                Toast.makeText(this, "WhatsApp tidak ditemukan di HP Anda", Toast.LENGTH_SHORT).show()
            }
        }
    }
}