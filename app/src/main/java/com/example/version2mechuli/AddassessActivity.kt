package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivityAddassessBinding
import com.example.version2mechuli.databinding.ActivityGetrecomBinding

class AddassessActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddassessBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addassess)

        binding = ActivityAddassessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }
    }
}