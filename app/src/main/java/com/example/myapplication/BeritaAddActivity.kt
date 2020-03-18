package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_berita_add.*
import org.json.JSONArray

class BeritaAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita_add)

        btn_add.setOnClickListener {
            val judul = editText1.text.toString()
            val waktu = editText2.text.toString()
            val penulis = editText3.text.toString()
            val isi = editText4.text.toString()
            Log.i("proses_add", judul + isi + waktu + penulis)
            postServer(judul, waktu, penulis, isi)

            finish()
            val intent = Intent(this, BeritaActivity::class.java)
            startActivity(intent)
        }
    }

    fun postServer(data1: String, data2: String, data3: String, data4: String)
    {
        AndroidNetworking.post("http://192.168.137.1/berita/add_berita.php")
            .addBodyParameter("judul_berita", data1)
            .addBodyParameter("waktu_berita", data2)
            .addBodyParameter("penulis_berita", data3)
            .addBodyParameter("isi_berita", data4)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONArray(object : JSONArrayRequestListener
            {
                override fun onResponse(response: JSONArray)
                {

                }
                override fun onError(anError: ANError?)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
