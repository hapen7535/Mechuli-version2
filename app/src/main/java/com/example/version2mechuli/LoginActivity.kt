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
    lateinit var SendId : String
    lateinit var SendPw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SendId = binding.loginID.text.toString()
        SendPw = binding.loginPW.text.toString()

        binding.loginBtn.setOnClickListener{
            sendLoginData(SendId, SendPw)
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

    private fun sendLoginData(userid : String, userpw : String){

        lifecycleScope.launch{
            val res = withContext(Dispatchers.IO){
                InfoClientLogin.service.requestData(userid,userpw)
            }
            val answer = res.isUser
            if(answer){
                val intent = Intent(this@LoginActivity, CustomerActivity::class.java)
                Toast.makeText(this@LoginActivity, "로그인이 완료되었습니다.", Toast.LENGTH_LONG).show()
//                intent.putExtra("id", userid)
                val editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit()
                editor.putString("userId", userid)
                editor.apply()
                startActivity(intent)
            } else{
                Toast.makeText(this@LoginActivity, "아이디 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

    }

//    private fun ActivityStart(userid : String){
//        val intent = Intent(this, CustomerActivity::class.java)
//        intent.putExtra("id", userid)
//        startActivity(intent)
//    }

}