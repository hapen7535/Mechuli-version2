package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
//import com.bumptech.glide.Glide
import com.example.version2mechuli.databinding.ActivitySigndataBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SigndataActivity : AppCompatActivity() {

    lateinit var binding : ActivitySigndataBinding
    lateinit var arrMenu : ArrayList<String>
    lateinit var userid : String
    lateinit var userpw : String
    lateinit var imgList : ArrayList<String>

    var ratingList = mutableMapOf<String, Float>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signdata)


        val secondIntent = getIntent()
        userid = secondIntent.getStringExtra("id").toString()
        userpw = secondIntent.getStringExtra("pw").toString()
        arrMenu = arrayListOf("떡볶이","파스타","쌈밥","라멘","와플")

        getMenuImg(arrMenu)

        binding = ActivitySigndataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

        addMenuView(arrMenu)

        binding.signTest.setOnClickListener {
            if(ratingList.size < 5){
                Toast.makeText(this, "해당 메뉴의 점수를 모두 매겨주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                completeInfo(userid, userpw, ratingList)
            }
        }
    }

    private fun addMenuView(arr : ArrayList<String>){


        arr.forEach{

            val menuView = layoutInflater.inflate(R.layout.menutest_layout, null, false)
            val menuViewText = menuView.findViewById<TextView>(R.id.menuName)
            val menuRating = menuView.findViewById<RatingBar>(R.id.ratingBar)
            val rating = menuRating.rating

            menuViewText.setText(it)
            binding.addMenu.addView(menuView)

            menuRating.setOnRatingBarChangeListener{ ratingBar, rating, fromUser->
                ratingBar.rating
                ratingList[it] = rating
                Log.d("ratingList", ratingList.toString())
            }
        }
    }

    private fun getMenuImg(nameList : ArrayList<String>){

        val imgList  = arrayListOf<String>()
            lifecycleScope.launch {
                Log.d("myTag", "서버 요청 실행")
                //UI
                val res = withContext(Dispatchers.IO) {
                    InfoClientMenuImg.service.requestData(nameList)
                }
                //UI
                Log.d("myTag", "서버 데이터 받음 : " + res)
                Log.d("myTag", "0 : " + res.resultList[0])
                Log.d("myTag", "1 : " + res.resultList[1])
//                Log.d("myTag", "0 : " + res.get(0))
//                Log.d("myTag", "1 : " + res.get(1))
//                Log.d("myTag", "2 : " + res.get(2))
//                Log.d("myTag", "3 : " + res.get(3))
//                Log.d("myTag", "4 : " + res.get(4))
//                Log.d("myTag", res.get(0).resultList.toString())
//                Log.d("myTag", "서버 데이터 받음" + res.get(0).foodName)

            }

    }

    private fun completeInfo(id : String, pw : String, ratings : MutableMap<String, Float>){
        lifecycleScope.launch{
            //UI
            val res = withContext(Dispatchers.IO){
                InfoClientData.service.requestData(id,pw,ratings)
            }
            //UI
            val answer = res.result
            if(answer){
                val intent = Intent(this@SigndataActivity, LoginActivity::class.java)
                Toast.makeText(this@SigndataActivity, "회원가입 처리가 정상적으로 완료되었습니다.", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else{
                Toast.makeText(this@SigndataActivity, "회원가입 처리가 비정상적으로 처리되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }


}