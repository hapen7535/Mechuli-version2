package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
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
            completeInfo(userid, userpw)
        }


    }

    private fun addMenuView(arr : ArrayList<String>){

        arr.forEach{

            val menuView = layoutInflater.inflate(R.layout.menutest_layout, null, false)
            val menuViewText = menuView.findViewById<TextView>(R.id.menuName)

            menuViewText.setText(it)
            binding.addMenu.addView(menuView)

            menuView.setOnClickListener {
                //tagView.setBackgroundResource(R.drawable.taglayout);
            }
        }

    }

    private fun completeInfo(id : String, pw : String){

        lifecycleScope.launch{
            //UI
            val res = withContext(Dispatchers.IO){
                InfoClientData.service.requestData(id,pw)
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