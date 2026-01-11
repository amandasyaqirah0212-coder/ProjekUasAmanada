package com.xample.projek_uas

import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class PromoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        // 1. Inisialisasi Tombol Back
        val btnBack = findViewById<ImageButton>(R.id.btnBackPromo)
        btnBack?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Lebih aman daripada finish()
        }

        // 2. Inisialisasi Card Voucher
        val voucher1 = findViewById<CardView>(R.id.cardVoucher1)
        val voucher2 = findViewById<CardView>(R.id.cardVoucher2)
        val voucher3 = findViewById<CardView>(R.id.cardVoucher3)
        val voucher4 = findViewById<CardView>(R.id.cardVoucher4)

        // 3. Set Klik untuk masing-masing voucher dengan Logic Simpan
        voucher1?.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
            klaimVoucher("DISKON50", "Diskon 50% Berhasil Diklaim!")
        }

        voucher2?.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
            klaimVoucher("FREE_ONGKIR", "Gratis Ongkir Berhasil Diklaim!")
        }

        voucher3?.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
            klaimVoucher("CASHBACK10", "Cashback 10RB Siap Digunakan!")
        }

        voucher4?.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
            klaimVoucher("BUY1GET1", "Promo Buy 1 Get 1 Aktif!")
        }
    }

    // Fungsi sakti buat simpan voucher ke memori HP
    private fun klaimVoucher(kodeVoucher: String, pesan: String) {
        val sharedPref = getSharedPreferences("DATA_PROMO", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("VOUCHER_AKTIF", kodeVoucher)
        editor.apply()

        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()

        // Opsional: Setelah klaim, langsung balik ke Home
        // finish()
    }
}