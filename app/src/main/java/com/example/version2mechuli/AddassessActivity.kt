package com.example.version2mechuli

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.version2mechuli.databinding.ActivityAddassessBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddassessActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddassessBinding
    lateinit var menuList : List<String>
    lateinit var menuImgList : List<String>
    lateinit var menuRatingList : List<Float>
    lateinit var adapter : ArrayAdapter<String>

    var ratingList = mutableMapOf<String, Float>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_addassess)

//        binding = ActivityAddassessBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addassess)
        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        val userid = spf.getString("userId", "")!!
        val inputWord = binding.searchMenu.text

        getMenuList(userid)

        binding.assess = Description("메뉴를 선택하여\n 해당 메뉴의 평점을 수정하거나 추가해주세요.")

        binding.ratingBar.setOnRatingBarChangeListener{ ratingBar, rating, fromUser->

            ratingList[binding.menuName.toString()] = binding.ratingBar.rating
            setNewRating(userid, ratingList)

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }


    }

    private fun setNewRating(id : String, ratings : MutableMap<String, Float>){

        lifecycleScope.launch {
            //UI
            val res = withContext(Dispatchers.IO) {
                InfoClientRating.service.requestData(id, ratingList)
            }
            val answer = res.result
            if(answer){
                Toast.makeText(this@AddassessActivity, "평점을 수정하였습니다.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@AddassessActivity, "평점을 수정할 수 없습니다.", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getMenuList(id: String){
        lifecycleScope.launch{
            //UI
            val res = withContext(Dispatchers.IO){
                InfoClientMenu.service.getData(id)
            }
            Log.d("myTag", "서버에서 데이터 받음, " + res)
            Log.d("myTag", "0 : " + res.menuList[0])
            Log.d("myTag", "1 : " + res.menuList[1])

            //UI
            val answer = res.menuList
            if(answer.isNotEmpty()){
                menuList = answer.map{ it.foodName }
                menuImgList = answer.map{ it.image_2 }
                menuRatingList = answer.map{ it.rating }
                adapter = ArrayAdapter(
                    this@AddassessActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    menuList
                )
                binding.searchMenu.setAdapter(adapter)

                binding.searchMenu.setOnItemClickListener { parent, view, position, id ->

                    binding.menuLayout.visibility = View.VISIBLE
                    val selection = parent.getItemAtPosition(position)
                    var pos = -1

                    for (i in 0 until menuList.size) {
                        if (menuList.get(i).equals(selection)) {
                            pos = i
                            break
                        }
                    }

                    binding.menuName.setText(menuList[pos])
                    binding.ratingBar.rating = menuRatingList[pos]
                    Glide.with(this@AddassessActivity)
                        .load(menuImgList[pos])
                        .into(binding.menuImg)

                }

            } else{
                Log.d("getMenuResult", "메뉴 리스트를 불러올 수 없습니다.")
            }
        }
    }



}