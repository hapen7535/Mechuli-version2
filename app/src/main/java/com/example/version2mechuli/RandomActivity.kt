package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.version2mechuli.databinding.ActivityLoginBinding
import com.example.version2mechuli.databinding.ActivityRandomBinding

class RandomActivity : AppCompatActivity() {

    lateinit var binding : ActivityRandomBinding
    lateinit var arrTag : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

            //arrTag = arrayListOf("전체","분식","한식","양식","중식")
            //addTagView(arrTag)

        binding.returnbtn.setOnClickListener {
            onBackPressed()
        }


    }

    private fun addTagView(arr : ArrayList<String>){


        arr.forEach{

            val tagView = layoutInflater.inflate(R.layout.tags, null, false)
            val tagViewText = tagView.findViewById<TextView>(R.id.tagRadio)

            tagViewText.setText(it)
            binding.addTag.addView(tagView)

            tagView.setOnClickListener {
                //tagView.setBackgroundResource(R.drawable.taglayout);
            }
        }

    }

}