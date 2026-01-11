package com.xample.projek_uas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import java.util.Locale

class DeliveryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        // 1. Inisialisasi View berdasarkan ID di XML terbaru kamu
        val txtLokasiAktif = findViewById<TextView>(R.id.menuAlamat) // Menggunakan ID menuAlamat sesuai diskusi
        val btnKonfirmasi = findViewById<Button>(R.id.btnConfirmLocation) // Sesuai ID di button bawah
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        // 2. Tombol Kembali
        btnBack?.setOnClickListener {
            finish()
        }

        // 3. Cek instruksi dari AboutActivity
        val action = intent.getStringExtra("ACTION")
        if (action == "TAMBAH_LOKASI") {
            Toast.makeText(this, "Mendeteksi alamat Anda...", Toast.LENGTH_SHORT).show()
            ambilLokasiOtomatis(txtLokasiAktif)
        }

        // 4. Klik Konfirmasi -> Kirim data kembali ke Profil
        btnKonfirmasi?.setOnClickListener {
            val alamatTerdeteksi = txtLokasiAktif.text.toString()

            if (alamatTerdeteksi.isNotEmpty() && alamatTerdeteksi != "Sedang mencari lokasi...") {
                val resultIntent = Intent()
                resultIntent.putExtra("ALAMAT_SELESAI", alamatTerdeteksi) // Key harus sama dengan di AboutActivity
                setResult(RESULT_OK, resultIntent)

                Toast.makeText(this, "Lokasi Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                finish() // Menutup activity dan kembali
            } else {
                Toast.makeText(this, "Alamat belum terdeteksi GPS", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi deteksi GPS
    private fun ambilLokasiOtomatis(textView: TextView) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    try {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        val alamatLengkap = addresses?.get(0)?.getAddressLine(0)
                        textView.text = alamatLengkap ?: "Alamat tidak ditemukan"
                    } catch (e: Exception) {
                        textView.text = "Gagal mengambil nama jalan"
                    }
                } else {
                    textView.text = "Gagal deteksi koordinat. Pastikan GPS menyala."
                }
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }
}