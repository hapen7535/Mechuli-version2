package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.version2mechuli.databinding.ActivityAddassessBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddassessActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddassessBinding
    lateinit var menuList : List<String>
    lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_addassess)

//        binding = ActivityAddassessBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addassess)
        getMenuList()

        binding.assess = Description("메뉴를 선택하여 해당 메뉴의 평점을 수정하거나 추가해주세요.")

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }


    }

    private fun getMenuList(id : String){
        lifecycleScope.launch{
            //UI
            val res = withContext(Dispatchers.IO){
                InfoClientMenu.service.getData(id)
            }
            //UI
            val answer = res.menuList
            if(answer.isNotEmpty()){
                menuList = answer.map{ it.foodName }
                adapter = ArrayAdapter(
                    this@AddassessActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    menuList
                )
                binding.searchMenu.setAdapter(adapter)
            } else{
                Log.d("getMenuResult", "메뉴 리스트를 불러올 수 없습니다.")
            }
        }
    }



}