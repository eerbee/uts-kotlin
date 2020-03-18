package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_berita.*
import org.json.JSONArray
import org.json.JSONObject

class BeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)

        getServer()

        btn_add.setOnClickListener {
            val intent = Intent(this, BeritaAddActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    fun getServer()
    {
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val slides = ArrayList<Berita>()
        AndroidNetworking.get("http://192.168.137.1/berita/berita_show.php")
            .setPriority(Priority.MEDIUM).build().getAsJSONObject(object :
                JSONObjectRequestListener
            {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        var isi1 = jsonObject.optString("judul_berita").toString()
                        var isi2 = jsonObject.optString("waktu_berita").toString()
                        var isi3 = jsonObject.optString("penulis_berita").toString()
                        var isi4 = jsonObject.optString("isi_berita").toString()
                        slides.add(Berita("$isi1", "$isi2", "$isi3", "$isi4"))
                    }
                    val adapter = BeritaAdapter(slides)
                    recyclerView.adapter = adapter
                }
                override fun onError(anError: ANError?)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    override fun onBackPressed()
    {
        val intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)
    }
}
