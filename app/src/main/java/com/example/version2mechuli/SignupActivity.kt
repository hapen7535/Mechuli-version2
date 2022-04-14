package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.version2mechuli.databinding.ActivityLoginBinding
import com.example.version2mechuli.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dupChk.setOnClickListener{

            //중복되는 아이디라면
           // binding.dupText.visibility = View.VISIBLE

            //중복되지 않는 아이디라면
          //  binding.noneDup.visibility = View.VISIBLE

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }


    fun whatever(view: View) {

        //Log.d("click","눌림")
        val intent = Intent(this, SigndataActivity::class.java)
        startActivity(intent)

    }

}