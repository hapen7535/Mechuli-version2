package com.example.version2mechuli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivitySignupBinding
import com.example.version2mechuli.databinding.ActivityStartBinding

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)

        binding.signStart.setOnClickListener{

            val intent = Intent(this, SigndataActivity::class.java)
            startActivity(intent)

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }
}