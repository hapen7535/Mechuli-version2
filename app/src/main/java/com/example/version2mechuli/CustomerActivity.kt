package com.example.version2mechuli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {

    lateinit var binding : ActivityCustomerBinding
    lateinit var userid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val secondIntent = getIntent()
        userid = secondIntent.getStringExtra("id").toString()

        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetId(userid)

        binding.addCheck.setOnClickListener{

            val intent = Intent(this, AddassessActivity::class.java)
            startActivity(intent)

        }

        binding.getRecom.setOnClickListener{

            val intent = Intent(this, GetrecomActivity::class.java)
            startActivity(intent)

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }

    private fun GetId(id : String){
        binding.userID.setText(id)
    }

}