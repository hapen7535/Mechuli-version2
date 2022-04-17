package com.example.version2mechuli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.example.version2mechuli.databinding.ActivityLoginBinding
import com.example.version2mechuli.databinding.ActivityStartBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, CustomerActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

        //회원가입 텍스트에 밑줄
        val content = SpannableString(binding.signUp.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.signUp.text = content


    }
}