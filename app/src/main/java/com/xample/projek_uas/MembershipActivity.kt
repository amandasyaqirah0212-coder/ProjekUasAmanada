package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MembershipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)

        // 1. Tombol Back
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack?.setOnClickListener {
            finish()
        }

        // 2. Tombol Ubah Profil (ID disamakan dengan XML: btnUbahProfil)
        val btnEdit = findViewById<Button>(R.id.btnUbahProfil)

        btnEdit?.setOnClickListener {
            // Pindah ke halaman EditProfilActivity
            val intent = Intent(this, EditProfilActivity::class.java)
            startActivity(intent)
        }
    }
}