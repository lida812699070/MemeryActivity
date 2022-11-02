package com.example.memeryactivity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val data = intent.getStringExtra("data")
        val btn2 = findViewById<TextView>(R.id.tvBtn2)
        btn2.text = data
        btn2.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag1", "MainActivity2 onDestroy")
    }
}