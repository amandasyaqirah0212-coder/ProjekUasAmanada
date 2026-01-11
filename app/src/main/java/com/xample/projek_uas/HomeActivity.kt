package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. LINK TOMBOL MENU (DI TENGAH)
        findViewById<CardView>(R.id.cardMenuKopi)?.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        // 2. LINK TOMBOL PROMO (DI TENGAH)
        findViewById<CardView>(R.id.cardPromo)?.setOnClickListener {
            val intent = Intent(this, PromoActivity::class.java)
            startActivity(intent)
        }

        // 3. LINK TOMBOL DELIVERY (DI TENGAH)
        findViewById<CardView>(R.id.cardDelivery)?.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)
        }

        // 4. LINK NAVIGASI BAWAH (BOTTOM NAV)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true

                R.id.nav_promo -> {
                    startActivity(Intent(this, PromoActivity::class.java))
                    true
                }

                R.id.nav_pesanan -> {
                    val intent = Intent(this, PesananActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_profile -> {
                    // --- PERBAIKAN DI SINI ---
                    // Tujuannya harus ke AboutActivity agar muncul profil Amanda dulu
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}