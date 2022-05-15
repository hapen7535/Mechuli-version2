package com.example.version2mechuli
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version2mechuli.InfoClient
import com.example.version2mechuli.R
import com.example.version2mechuli.SigndataActivity
import com.example.version2mechuli.databinding.ActivitySignupBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding
    private val idch by lazy { findViewById<EditText>(R.id.setID)}
    //private val checkPoint by lazy { findViewById<TextView>(R.id.checkpoint) }
    private val callList = mutableListOf<Call<*>>()

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
                //sendUserInfo(userId.toString(), userPw.toString())
                id = userId.toString()
                pw = userPw.toString()
                ActivityStart(id, pw)
            }
        }

        binding.returnbtn.setOnClickListener{
            onBackPressed()
        }


    }

    fun checkId(view: View){

        lifecycleScope.launch{
            //UI
            val id = idch.text.toString()
            val result = withContext(Dispatchers.IO){
                InfoClient.service.requestData(id)
            }
            //UI
            val chkAns = result.isRedup
            if(chkAns){
                binding.dupText.text = "사용하실 수 있는 아이디입니다."
                binding.signStart.isEnabled = true
            }
            else{
                binding.dupText.text = "이미 해당 아이디가 사용중입니다."
                binding.signStart.isEnabled = false
            }
        }
    }

    private fun ActivityStart(userid : String, userpw : String){
        val intent = Intent(this, SigndataActivity::class.java)
        intent.putExtra("id", userid)
        intent.putExtra("pw",userpw)
        startActivity(intent)
    }


//    private fun sendUserInfo(id : String, pw : String) {
//
//
//
//        var gson = GsonBuilder().setLenient().create()
//
//        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
//            .baseUrl("http://10.0.2.2:3333/")
//            .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱등록
//            .build()
//
//
//        if(id != null && pw != null ) {
//
////            Toast.makeText(applicationContext, "id : " + id + ", pw : " + pw, Toast.LENGTH_LONG).show()
//            var sendUserdata: SendUserdata = retrofit.create(SendUserdata::class.java) //아이디, 비밀번호 전송
//
//            sendUserdata.requestData(id, pw).enqueue(object :
//                Callback<GetData> {
//                override fun onFailure(call: Call<GetData>, t: Throwable) { //통신 실패
//                    Toast.makeText(applicationContext, "통신 실패 : " + t.message, Toast.LENGTH_LONG).show()
//                    Log.d("myTag", (t.message.toString()))
////                    Log.d("error", (t.message.toString()))
//                }
//                override fun onResponse(call : Call<GetData>, response: Response<GetData>){ //통신 성공
//                    var arr = response.body()
//                    Log.d("myTag", "res : " + arr.toString())
////                    Log.d("id,pw",(arr?.userId + arr?.password))
//                }
//            })
//        }
//
//        }


}


