package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.version2mechuli.api.InfoClientRandom
import com.example.version2mechuli.databinding.ActivityRandomBinding
import kotlinx.coroutines.*

class RandomActivity : AppCompatActivity() {

    lateinit var binding : ActivityRandomBinding
    lateinit var arrTag : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgList = arrayListOf(
            binding.menuImg1,
            binding.menuImg2,
            binding.menuImg3,
            binding.menuImg4,
            binding.menuImg5
        )
        val textList = arrayListOf(
            binding.menuName1,
            binding.menuName2,
            binding.menuName3,
            binding.menuName4,
            binding.menuName5
        )
        val menuLayouts =
            arrayListOf(binding.menu1, binding.menu2, binding.menu3, binding.menu4, binding.menu5)

        getRandomMenuList(imgList, textList, menuLayouts)

        binding.returnbtn.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }

//    private fun addTagView(arr : ArrayList<String>){
//        arr.forEach{
//
//            val tagView = layoutInflater.inflate(R.layout.tags, null, false)
//            val tagViewText = tagView.findViewById<TextView>(R.id.tagRadio)
//
//            tagViewText.setText(it)
//            binding.addTag.addView(tagView)
//
//            tagView.setOnClickListener {
//                //tagView.setBackgroundResource(R.drawable.taglayout);
//            }
//        }
//    }

    private fun getRandomMenuList(imgLayout: ArrayList<ImageView>, textlayout: ArrayList<TextView>, menuList: ArrayList<LinearLayout>) {
        var id = ""
        var j = 0
        var ele = ArrayList<String>()

        lifecycleScope.launch {
            Log.d("myTag", "Random menuList 가져오기")
            //UI
            val res = withContext(Dispatchers.IO) {
                InfoClientRandom.service.requestData(id)
            }


            Log.d("myTag", "결과 값 : " + res)
            Toast.makeText(this@RandomActivity, "평점 가져오기 완료, 상단 탭을 클릭해주세요.", Toast.LENGTH_LONG).show()

            var answer = res.menuList

            answer.forEach {
                textlayout[j].setText(it.foodName)

                Glide.with(this@RandomActivity)
                    .load(it.image_2)
                    .into(imgLayout[j])

                ele.add(it.type_1)

                Log.d("ele", "${ele}")
                j += 1
            }

            if(ele.size == 5) {

            binding.addTag.setOnCheckedChangeListener { group, checkedId ->

                    when (checkedId) {

                        R.id.tagAll -> {
                            menuList.forEach {
                                it.visibility = View.VISIBLE
                            }
                        }
                        R.id.tagChi -> {

                            var i = 0

                            ele.forEach {

                                if (it.equals("중식")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }
                        R.id.tagSna -> {
                            var i = 0
                            ele.forEach {
                                if (it.equals("분식")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }
                        R.id.tagWes -> {
                            var i = 0
                            ele.forEach {
                                if (it.equals("양식")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }
                        R.id.tagKor -> {
                            var i = 0
                            ele.forEach {
                                if (it.equals("한식")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }
                        R.id.tagJap -> {

                            var i = 0

                            ele.forEach {

                                if (it.equals("일식")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }
                        R.id.tagEtc -> {

                            var i = 0

                            ele.forEach {

                                if (it.equals("이외")) {
                                    menuList[i].visibility = View.VISIBLE
                                } else {
                                    menuList[i].visibility = View.GONE
                                }
                                i += 1
                            }
                        }

                    }

                }

            }

            }

        }



}