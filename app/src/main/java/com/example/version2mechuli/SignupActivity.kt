package com.example.version2mechuli
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
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
    private val callList = mutableListOf<Call<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ageList = resources.getStringArray(R.array.ageArray)
        val myAgeAdapterr = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageList)
        myAgeAdapterr.setDropDownViewResource(R.layout.age_spinner)
        binding.ageSpinner.adapter = myAgeAdapterr
        binding.ageSpinner.prompt = "연령대를 선택해주세요"


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
//                Log.d("myTag", "비밀번호 불일치")
            }
            else {
//                Log.d("myTag", "평가이력 체크로 이동")
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
                Log.d("myTag", "사용 가능, id : " + id)
                binding.dupText.text = "사용하실 수 있는 아이디입니다."
                binding.signStart.isEnabled = true
            }
            else{
                Log.d("myTag", "사용 불가, id : " + id)
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


}


