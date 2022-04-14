package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.version2mechuli.databinding.ActivityGetrecomBinding
import com.example.version2mechuli.databinding.ActivityLoginBinding

class GetrecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetrecomBinding
    lateinit var arrMenu : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getrecom)

        binding = ActivityGetrecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

        arrMenu = arrayListOf("떡볶이","파스타","비빔밥","라멘","아이스크림")
        addMenuView(arrMenu)

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

}