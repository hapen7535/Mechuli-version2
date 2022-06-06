package com.example.version2mechuli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.version2mechuli.databinding.ActivityGetrecomBinding
import com.example.version2mechuli.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetrecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetrecomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetrecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        val userid = spf.getString("userId", "")!!

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

        getRecommendMenuList(userid, imgList, textList, menuLayouts)

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }
    }

    private fun init(){
        showProgress(false)
    }

    fun showProgress(isShow : Boolean){
        if(isShow) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun getRecommendMenuList(id : String, imgLayout: ArrayList<ImageView>, textlayout: ArrayList<TextView>, menuList: ArrayList<LinearLayout>) {

        var j = 0
        var ele = ArrayList<String>()


        lifecycleScope.launch {
            Log.d("myTag", "menuList 가져오기")
            //UI
            val res = withContext(Dispatchers.IO) {
                InfoClientRecommend.service.requestData(id)
            }
            Log.d("myTag", "결과 값 : " + res)

            var answer = res.menuList

            if (answer.isEmpty()) {
                init()
            } else {

                answer.forEach {
                    textlayout[j].setText(it.foodName)

                    Glide.with(this@GetrecomActivity)
                        .load(it.image_2)
                        .into(imgLayout[j])

                    ele.add(it.type_1)

                    Log.d("ele", "${ele}")
                    j += 1
                }

                if (ele.size == 5) {

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

                                    if (it.equals("이가")) {
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

}