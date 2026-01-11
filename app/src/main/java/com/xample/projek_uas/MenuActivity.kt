package com.xample.projek_uas

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // 1. TOMBOL KELUAR (Tanda X)
        findViewById<ImageButton>(R.id.btnBackMenu).setOnClickListener {
            finish()
        }

        // 2. DAFTAR KLIK MENU (Sesuaikan ID dengan yang ada di desain kuningmu)
        // Jika masih MERAH, ganti nama ID di bawah ini sesuai yang kamu tulis di XML

        aturKlik(R.id.btnDeleteStrawberry, "Strawberry", "Rp 22.000")
        aturKlik(R.id.btnDeleteKopiHitam, "Kopi Hitam", "Rp 15.000")
        aturKlik(R.id.btnDeletecappuccino, "Cappuccino", "Rp 25.000")
        aturKlik(R.id.btnDeletekopiSusu, "Kopi Susu", "Rp 18.000")
        aturKlik(R.id.btnDeleteBlueberry, "Blueberry", "Rp 24.000")
        aturKlik(R.id.btnDeleteMango, "Mango", "Rp 22.000")
        aturKlik(R.id.btnDeleteMocha, "Mocha", "Rp 28.000")
        aturKlik(R.id.btnDeleteCaramel, "Caramel", "Rp 30.000")
    }

    private fun aturKlik(idTombol: Int, nama: String, harga: String) {
        // Kita gunakan ImageButton atau ImageView tergantung apa yang kamu pakai di XML
        val tombol = findViewById<ImageButton>(idTombol)
        tombol?.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("pesanan_masuk")
            val dataPesanan = mapOf(
                "nama_produk" to nama,
                "harga" to harga,
                "waktu" to System.currentTimeMillis()
            )

            database.push().setValue(dataPesanan).addOnSuccessListener {
                Toast.makeText(this, "$nama Berhasil Masuk Firebase!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}