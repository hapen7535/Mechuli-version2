package com.example.version2mechuli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.version2mechuli.databinding.ActivityLoginBinding
import com.example.version2mechuli.databinding.ActivityStartBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var userid : String
    lateinit var userpw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userid = binding.loginID.text.toString()
        userpw = binding.loginPW.text.toString()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, CustomerActivity::class.java)
            sendLoginData(userid, userpw)
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

    private fun sendLoginData(id : String, pw : String){

        lifecycleScope.launch{
            val res = withContext(Dispatchers.IO){
                InfoClientLogin.service.requestData(id,pw)
            }
            val answer = res.isUser
            if(answer){
                val intent = Intent(this@LoginActivity, CustomerActivity::class.java)
                Toast.makeText(this@LoginActivity, "로그인이 완료되었습니다.", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else{
                Toast.makeText(this@LoginActivity, "아이디 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

    }

}