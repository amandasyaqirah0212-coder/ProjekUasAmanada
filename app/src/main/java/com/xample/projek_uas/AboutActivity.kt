package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.* // Tambahkan import Firebase

class AboutActivity : AppCompatActivity() {

    private lateinit var menuAlamat: TextView
    private lateinit var txtJumlahPesanan: TextView // Tambahan untuk info pesanan

    private val getAlamatResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val alamatBaru = result.data?.getStringExtra("ALAMAT_SELESAI")
            if (alamatBaru != null) {
                menuAlamat.text = alamatBaru
                Toast.makeText(this, "Alamat diperbarui!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Inisialisasi View
        menuAlamat = findViewById(R.id.menuAlamat)
        val menuMembership = findViewById<TextView>(R.id.menuMembership)
        val menuTantangan = findViewById<TextView>(R.id.menuTantangan)
        val menuBantuan = findViewById<TextView>(R.id.menuBantuan)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Asumsi kamu punya TextView di XML About untuk cek pesanan
        // Jika tidak ada, kamu bisa gunakan menuTantangan atau menu lainnya sebagai tombol cek pesanan
        val menuCekPesanan = findViewById<TextView>(R.id.menuTantangan)

        // 1. Logika untuk Cek Pesanan yang Masuk ke Firebase
        menuCekPesanan?.setOnClickListener {
            val intent = Intent(this, PesananActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Melihat daftar pesanan kamu", Toast.LENGTH_SHORT).show()
        }

        // 2. Klik Alamat Tersimpan
        menuAlamat.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            intent.putExtra("ACTION", "TAMBAH_LOKASI")
            getAlamatResult.launch(intent)
        }

        // 3. Navigasi Menu Lainnya
        menuMembership?.setOnClickListener {
            startActivity(Intent(this, MembershipActivity::class.java))
        }

        menuBantuan?.setOnClickListener {
            startActivity(Intent(this, PusatBantuanActivity::class.java))
        }

        btnEditProfile?.setOnClickListener {
            startActivity(Intent(this, EditProfilActivity::class.java))
        }

        // 4. Logika Bottom Navigation
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }

        // 5. Logout
        btnLogout?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}