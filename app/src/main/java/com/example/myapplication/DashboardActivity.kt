package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btn_berita.setOnClickListener {
            val intent = Intent(this, BeritaActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("STATUS", "0")
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            true
        }
    }
}
