package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.version2mechuli.databinding.ActivitySigndataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable

class SigndataActivity : AppCompatActivity() {

    lateinit var binding : ActivitySigndataBinding
    lateinit var arrMenu : ArrayList<String>
    lateinit var userid : String
    lateinit var userpw : String

    var ratingList = mutableMapOf<String, Float>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signdata)


        val secondIntent = getIntent()
        userid = secondIntent.getStringExtra("id").toString()
        userpw = secondIntent.getStringExtra("pw").toString()
        arrMenu = arrayListOf("떡볶이","파스타","비빔밥","라멘","아이스크림")

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