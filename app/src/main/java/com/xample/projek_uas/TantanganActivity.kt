package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TantanganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tantangan)

        // Tombol Kembali
        val btnBack = findViewById<ImageView>(R.id.btnBackTantangan)
        btnBack.setOnClickListener {
            finish()
        }

        // Tombol Mulai Pesan (Arahkan ke Home)
        val btnMulai = findViewById<Button>(R.id.btnMulaiTantangan)
        btnMulai.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}