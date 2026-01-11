package com.xample.projek_uas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class CoffeeAdapter(private var list: ArrayList<Kopi>) : RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(h: ViewHolder, p: Int) {
        val item = list[p]
        h.txtNama.text = item.nama
        h.txtHarga.text = item.harga

        // 1. LOGIKA AGAR GAMBAR BERUBAH (TIDAK STRAWBERRY SEMUA)
        // Kita cek nama kopinya, lalu ganti gambarnya sesuai file di folder drawable kamu
        when {
            item.nama.contains("strawberry", true) -> h.imgProduk.setImageResource(R.drawable.minuman_strawberry)
            item.nama.contains("hitam", true) -> h.imgProduk.setImageResource(R.drawable.kopi)
            item.nama.contains("blueberry", true) -> h.imgProduk.setImageResource(R.drawable.minuman_blueberrey)
            item.nama.contains("mangga", true) -> h.imgProduk.setImageResource(R.drawable.minuman_mangga)
            item.nama.contains("caramel", true) -> h.imgProduk.setImageResource(R.drawable.kopi) // Ganti ke drawable caramel kamu
            else -> h.imgProduk.setImageResource(R.drawable.kopi)
        }

        // 2. BAGIAN KLIK GAMBAR KE FIREBASE
        h.imgProduk.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("pesanan_masuk")
            val orderId = database.push().key

            val dataPesanan = mapOf(
                "nama_produk" to item.nama,
                "harga" to item.harga,
                "waktu" to System.currentTimeMillis()
            )

            if (orderId != null) {
                database.child(orderId).setValue(dataPesanan)
                    .addOnSuccessListener {
                        // Muncul notifikasi jika data masuk ke database
                        Toast.makeText(h.itemView.context, "Pesanan ${item.nama} Masuk ke Firebase!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(h.itemView.context, "Gagal mengirim pesanan", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<Kopi>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtNama: TextView = v.findViewById(R.id.txtNamaKopi)
        val txtHarga: TextView = v.findViewById(R.id.txtHargaKopi)
        val imgProduk: ImageView = v.findViewById(R.id.imgProduk)
    }
}