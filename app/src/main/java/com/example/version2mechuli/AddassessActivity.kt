package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.version2mechuli.databinding.ActivityAddassessBinding
import com.example.version2mechuli.databinding.ActivityGetrecomBinding

class AddassessActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddassessBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_addassess)

//        binding = ActivityAddassessBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addassess)
        binding.assess = Description("메뉴를 선택하여 해당 메뉴의 평점을 수정하거나 추가해주세요.")

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }
    }
}