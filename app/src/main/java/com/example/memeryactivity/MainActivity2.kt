package com.example.memeryactivity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        findViewById<TextView>(R.id.tvBtn2).setOnClickListener {
            MyHandler().sendMessageDelayed(Message.obtain(), (10 * 60 * 1000).toLong())
            finish()
        }
    }

    class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.e("tag1", "handler 执行完成")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag1", "MainActivity2 onDestroy")
    }
}