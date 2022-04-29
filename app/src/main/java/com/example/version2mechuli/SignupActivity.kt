package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version2mechuli.databinding.ActivitySignupBinding
import com.google.gson.GsonBuilder
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

        var userId = binding.setID.text
        var userPw = binding.setPW.text
        var duplicatePw = binding.dupPw.text


        binding.signStart.setOnClickListener{

            var id = userId.toString()
            var pw = userPw.toString()
            var dpw = duplicatePw.toString()
            //Log.d("myTag", "id : " + id + ", pw : " + pw + ", dpw : " + dpw)

            if(!pw.equals(dpw)){
                Toast.makeText(applicationContext, "두 비밀번호가 같지 않습니다.", Toast.LENGTH_LONG).show()
                //Log.d("myTag", "id : " + id + ", pw : " + pw + ", dpw : " + dpw)
            }
            else {
                //Log.d("myTag", "id : " + id + ", pw : " + pw + ", dpw : " + dpw)
                sendUserInfo(userId.toString(), userPw.toString())
            }
        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }

    }

    fun checkBtn(view : View) {
        var id = binding.setID.text.toString()

        //중복되는 아이디라면
        if (checkId(id) == false) {
            binding.dupText.visibility = View.VISIBLE
            binding.signStart.isEnabled = false
        }

        //중복되지 않는 아이디라면
        if (checkId(id) == true) {
            binding.noneDup.visibility = View.VISIBLE
        }
    }

    private fun checkId(id: String): Boolean {
        var gson = GsonBuilder().setLenient().create()
        var checkBool: Boolean = false
        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
            .baseUrl("http://10.0.2.2:3333/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱등록
            .build()

        if (id != null) {

    //            Toast.makeText(applicationContext, "id : " + id + ", pw : " + pw, Toast.LENGTH_LONG).show()
            var sendUserid: SendUserId = retrofit.create(SendUserId::class.java) //아이디 전송

            sendUserid.requestData(id).enqueue(object : Callback<GetIdresult> {
                override fun onFailure(call: Call<GetIdresult>, t: Throwable) { //통신 실패
                    Toast.makeText(applicationContext, "통신 실패 : " + t.message, Toast.LENGTH_LONG)
                        .show()
                    Log.d("myTag", (t.message.toString()))
    //                    Log.d("error", (t.message.toString()))
                }

                override fun onResponse(
                    call: Call<GetIdresult>,
                    response: Response<GetIdresult>
                ) { //통신 성공
                    checkBool = response.body()!!.isRedup
                    Log.d("myTag", "res : " + checkBool) //중복되는지 결과
    //                    Log.d("id,pw",(arr?.userId + arr?.password))
                }
            })
        }

        return checkBool
    }


    private fun sendUserInfo(id : String, pw : String) {

        val intent = Intent(this, SigndataActivity::class.java)

        var gson = GsonBuilder().setLenient().create()

        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
            .baseUrl("http://10.0.2.2:3333/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱등록
            .build()


        if(id != null && pw != null && !checkId(id)) {

//            Toast.makeText(applicationContext, "id : " + id + ", pw : " + pw, Toast.LENGTH_LONG).show()
            var sendUserdata: SendUserdata = retrofit.create(SendUserdata::class.java) //아이디, 비밀번호 전송

            sendUserdata.requestData(id, pw).enqueue(object :
                Callback<GetData> {
                override fun onFailure(call: Call<GetData>, t: Throwable) { //통신 실패
                    Toast.makeText(applicationContext, "통신 실패 : " + t.message, Toast.LENGTH_LONG).show()
                    Log.d("myTag", (t.message.toString()))
//                    Log.d("error", (t.message.toString()))
                }
                override fun onResponse(call : Call<GetData>, response: Response<GetData>){ //통신 성공
                    var arr = response.body()
                    Log.d("myTag", "res : " + arr.toString())
//                    Log.d("id,pw",(arr?.userId + arr?.password))
                }
            })
        }


            startActivity(intent)
        }


    }

