package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivityGetrecomBinding
import com.example.version2mechuli.databinding.ActivityLoginBinding

class GetrecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetrecomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getrecom)

        binding = ActivityGetrecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }
}