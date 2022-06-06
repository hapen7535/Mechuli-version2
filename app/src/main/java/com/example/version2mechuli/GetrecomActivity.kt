package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.version2mechuli.databinding.ActivityGetrecomBinding
import com.example.version2mechuli.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetrecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetrecomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_getrecom)

        binding = ActivityGetrecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = "1111"
        getRecommendMenuList(id)

        if(binding.tagAll.isChecked){

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }
    }

    private fun getRecommendMenuList(id : String) {
        lifecycleScope.launch {
            Log.d("myTag", "menuList 가져오기")
            //UI
            val res = withContext(Dispatchers.IO) {
                InfoClientRecommend.service.requestData(id)
            }
            Log.d("myTag", "결과 값 : " + res)
        }
    }

}