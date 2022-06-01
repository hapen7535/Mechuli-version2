package com.example.version2mechuli

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
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
        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        val userid = spf.getString("userId", "")!!
        val inputWord = binding.searchMenu.text
        getMenuList(userid, inputWord)

        binding.assess = Description("메뉴를 선택하여 해당 메뉴의 평점을 수정하거나 추가해주세요.")

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }


    }

    private fun getMenuList(id: String, keyword: Editable){
        lifecycleScope.launch{
            //UI
            val res = withContext(Dispatchers.IO){
                InfoClientMenu.service.getData(id, keyword)
            }
            Log.d("myTag", "서버에서 데이터 받음, " + res)
            Log.d("myTag", "0 : " + res.menuList[0])
            Log.d("myTag", "1 : " + res.menuList[1])

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