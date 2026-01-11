package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*

class PesananActivity : AppCompatActivity() {

    // Tambahkan referensi database
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan)

        val tvNama = findViewById<TextView>(R.id.tvNamaPesanan)
        val tvHarga = findViewById<TextView>(R.id.tvHargaPesanan)
        val tvStatus = findViewById<TextView>(R.id.tvStatusPesanan)
        val cardPesanan = findViewById<CardView>(R.id.cardItemPesanan)
        val tvKosong = findViewById<TextView>(R.id.tvPesananKosong)
        val btnDelete = findViewById<ImageButton>(R.id.btnDeletePesanan)

        // 1. Inisialisasi Firebase ke node "pesanan_masuk"
        database = FirebaseDatabase.getInstance().getReference("pesanan_masuk")

        // 2. Ambil data terakhir yang masuk ke Firebase
        database.limitToLast(1).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        // Ambil nama dan harga sesuai key di CoffeeAdapter kamu
                        val nama = data.child("nama_produk").value.toString()
                        val harga = data.child("harga").value.toString()

                        cardPesanan.visibility = View.VISIBLE
                        tvKosong.visibility = View.GONE
                        tvNama.text = nama
                        tvHarga.text = harga
                        tvStatus.text = "Sedang Diproses"
                    }
                } else {
                    cardPesanan.visibility = View.GONE
                    tvKosong.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PesananActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // 3. Logika Hapus (Menghapus SEMUA pesanan di Firebase)
        btnDelete?.setOnClickListener {
            database.removeValue().addOnSuccessListener {
                Toast.makeText(this, "Semua pesanan dihapus dari Firebase", Toast.LENGTH_SHORT).show()
            }
        }

        // --- Logika Navigasi (Tetap sama) ---
        findViewById<Button>(R.id.btnKeluarPesanan)?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav?.selectedItemId = R.id.nav_pesanan
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { startActivity(Intent(this, HomeActivity::class.java)); finish(); true }
                R.id.nav_profile -> { startActivity(Intent(this, AboutActivity::class.java)); finish(); true }
                else -> false
            }
        }
    }
}