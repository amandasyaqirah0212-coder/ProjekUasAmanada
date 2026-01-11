package com.xample.projek_uas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan ke layout activity_edit_profil.xml
        setContentView(R.layout.activity_edit_profil)

        // 1. Inisialisasi semua komponen dari XML
        val imgFoto = findViewById<ImageView>(R.id.imgEditFoto)
        val etNama = findViewById<EditText>(R.id.etNamaBaru)
        val etEmail = findViewById<EditText>(R.id.etEmailBaru)
        val btnSimpan = findViewById<Button>(R.id.btnSimpanProfil)
        val btnCancel = findViewById<Button>(R.id.btncancelProfil) // <--- TAMBAHAN INI

        // 2. Fungsi klik pada Foto
        imgFoto.setOnClickListener {
            Toast.makeText(this, "Fitur buka Galeri akan segera siap!", Toast.LENGTH_SHORT).show()
        }

        // 3. Fungsi klik pada Tombol Simpan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()

            if (nama.isNotEmpty() && email.isNotEmpty()) {
                Toast.makeText(this, "Profil $nama Berhasil Diperbarui!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Harap isi Nama dan Email!", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Fungsi klik pada Tombol Cancel (TAMBAHAN BARU)
        btnCancel.setOnClickListener {
            // Menutup halaman ini dan kembali ke halaman sebelumnya (Home/Membership)
            finish()
        }
    }
}