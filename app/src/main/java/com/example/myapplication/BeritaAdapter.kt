package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeritaAdapter (val slideList: ArrayList<Berita>): RecyclerView.Adapter<BeritaAdapter.ViewHolder>()
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val user: Berita = slideList[position]
        holder.textViewJudul.text = user.judul
        holder.textViewWaktu.text = user.waktu
        holder.textViewPenulis.text = user.penulis
        holder.textViewIsi.text = user.isi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_berita, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewJudul = itemView.findViewById(R.id.judul_berita) as TextView
        val textViewWaktu = itemView.findViewById(R.id.waktu_berita) as TextView
        val textViewPenulis = itemView.findViewById(R.id.penulis_berita) as TextView
        val textViewIsi = itemView.findViewById(R.id.isi_berita) as TextView
    }
}