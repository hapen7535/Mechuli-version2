package com.example.version2mechuli.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.version2mechuli.R

class MainActivity : AppCompatActivity() {

    lateinit var startBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById<Button>(R.id.startbtn)

        title = "메추리"

        startBtn.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }


    }
}