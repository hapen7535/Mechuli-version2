package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version2mechuli.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {

    lateinit var binding : ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCheck.setOnClickListener{

            supportFragmentManager.beginTransaction()
                .replace(R.id.frm, AddassessFragment())
                .commitNowAllowingStateLoss()

        }

        binding.getRecom.setOnClickListener{

            supportFragmentManager.beginTransaction()
                .replace(R.id.frm, GetrecommendFragment())
                .commitNowAllowingStateLoss()

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }
}