package com.example.version2mechuli.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    lateinit var binding : ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noLogin.setOnClickListener{
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
        }
        binding.withLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }
}