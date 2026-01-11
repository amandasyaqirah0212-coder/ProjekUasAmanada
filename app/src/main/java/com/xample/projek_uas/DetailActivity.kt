package com.xample.projek_uas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetail = findViewById<ImageView>(R.id.imgDetail)
        val txtNama = findViewById<TextView>(R.id.txtNamaDetail)
        val txtHarga = findViewById<TextView>(R.id.txtHargaDetail)
        val txtDeskripsi = findViewById<TextView>(R.id.txtDeskripsiDetail)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnBack = findViewById<ImageButton>(R.id.btnBackDetail)

        val namaKopi = intent.getStringExtra("NAMA") ?: "Menu Kopi"
        val hargaKopi = intent.getStringExtra("HARGA") ?: "Rp 0"

        // Ambil ID gambar dari MenuActivity
        var gambarRes = intent.getIntExtra("GAMBAR", R.drawable.kopi_pahit)

        txtNama.text = namaKopi
        txtHarga.text = hargaKopi

        // --- LOGIKA TAMPILAN & PENENTUAN GAMBAR ---
        when (namaKopi) {
            "Strawberry Coffee" -> {
                gambarRes = R.drawable.minuman_strawberry
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Sensasi kopi creamy dengan campuran buah strawberry asli. Rasa manis dan asam segar yang sempurna untuk menemani sore hari Anda."
            }
            "Kopi Hitam" -> {
                gambarRes = R.drawable.kopi_pahit
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Kopi hitam murni dari biji kopi robusta pilihan. Tanpa ampas, aroma kuat, dan rasa yang berani bagi penikmat kopi sejati."
            }
            "Cappuccino" -> {
                gambarRes = R.drawable.kopi_cappuccino
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Keseimbangan sempurna antara espresso, steamed milk, dan foam tebal. Ditaburi bubuk cokelat di atasnya untuk rasa yang klasik."
            }
            "Kopi Susu" -> {
                gambarRes = R.drawable.kopi
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Resep tradisional kopi susu dengan kental manis premium. Rasa nyaman yang mengingatkan pada suasana santai di rumah."
            }
            "Blueberry" -> {
                gambarRes = R.drawable.minuman_blueberrey
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Inovasi latte dengan rasa buah blueberry yang segar. Memberikan rasa manis buah yang unik dan tekstur yang sangat halus."
            }
            "Mango Tango" -> {
                gambarRes = R.drawable.minuman_mangga
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Perpaduan kopi dengan ekstrak mangga tropis. Rasa eksotis yang memberikan kejutan di setiap seruputan. Sangat menyegarkan!"
            }
            "Caramel Latte" -> {
                gambarRes = R.drawable.minuman_taro
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Minuman taro lembut dengan manis rasanya yang khas menyatu dengan espresso yang rich."
            }
            "Kopi Mocha" -> {
                gambarRes = R.drawable.kopi_mocha
                imgDetail.setImageResource(gambarRes)
                txtDeskripsi.text = "Gabungan sempurna antara cokelat hitam dan kopi espresso. Cocok untuk Anda yang menyukai sentuhan cokelat di dalam kopi."
            }
            else -> {
                imgDetail.setImageResource(gambarRes)
            }
        }

        btnBack.setOnClickListener { finish() }

        // --- LOGIKA TOMBOL ORDER ---
        btnOrder.setOnClickListener {
            // 1. Simpan Data (Nama, Harga, dan ID Gambar)
            simpanPesananKeAplikasi(namaKopi, hargaKopi, gambarRes)

            // 2. Kirim ke WhatsApp
            val nomorWa = "6287711399166"
            val isiPesan = "Halo Admin, saya mau pesan menu: *$namaKopi* dengan harga *$hargaKopi*. Tolong segera diproses ya!"

            try {
                val url = "https://api.whatsapp.com/send?phone=$nomorWa&text=${Uri.encode(isiPesan)}"
                val intentWa = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentWa)

                // Pindah ke halaman pesanan setelah kirim WA
                val intentPesanan = Intent(this, PesananActivity::class.java)
                startActivity(intentPesanan)
                finish()

            } catch (e: Exception) {
                Toast.makeText(this, "WhatsApp tidak ditemukan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // FUNGSI SIMPAN (SEKARANG BISA SIMPAN GAMBAR JUGA)
    private fun simpanPesananKeAplikasi(nama: String, harga: String, gambarId: Int) {
        val sharedPref = getSharedPreferences("DATA_PESANAN", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("LAST_NAMA", nama)
        editor.putString("LAST_HARGA", harga)
        editor.putInt("LAST_GAMBAR", gambarId) // Simpan ID Gambar
        editor.putBoolean("ADA_PESANAN", true)
        editor.apply()
    }
}