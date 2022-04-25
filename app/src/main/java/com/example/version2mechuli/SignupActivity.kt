package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version2mechuli.databinding.ActivityLoginBinding
import com.example.version2mechuli.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userId = binding.setID.text.toString()
        var userPw = binding.setPW.text.toString()


        binding.dupChk.setOnClickListener{

            //중복되는 아이디라면
           // binding.dupText.visibility = View.VISIBLE

            //중복되지 않는 아이디라면
          //  binding.noneDup.visibility = View.VISIBLE

        }

        binding.signStart.setOnClickListener{
            sendUserInfo(userId, userPw)

        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }


    private fun sendUserInfo(id : String, pw : String) {

        val intent = Intent(this, SigndataActivity::class.java)

        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
            .baseUrl("http://3.39.194.151")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if(id != null && pw != null) {

            var sendUserdata: SendUserdata = retrofit.create(SendUserdata::class.java) //아이디, 비밀번호 전송

            sendUserdata.requestData(id, pw).enqueue(object :
                Callback<GetData> {
                override fun onFailure(call: Call<GetData>, t: Throwable) { //통신 실패
                    Toast.makeText(applicationContext, "통신 실패 : " + t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call : Call<GetData>, response: Response<GetData>){ //통신 성공
                    var arr = response.body()
                }
            })
        }


            startActivity(intent)
        }

    }

