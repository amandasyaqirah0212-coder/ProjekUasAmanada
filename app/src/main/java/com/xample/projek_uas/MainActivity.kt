package com.xample.projek_uas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xample.projek_uas.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fungsi klik tombol Login
        binding.btnLogin.setOnClickListener {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            // PINDAH DARI LOGIN KE HOME
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}