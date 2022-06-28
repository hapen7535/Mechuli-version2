package com.example.version2mechuli.view
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.version2mechuli.R
import com.example.version2mechuli.SignInfo
import com.example.version2mechuli.api.InfoClient
import com.example.version2mechuli.databinding.ActivitySignupBinding
import kotlinx.coroutines.*

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding
    private val idch by lazy { findViewById<EditText>(R.id.setID)}
    lateinit var signInfo : SignInfo

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ageList = resources.getStringArray(R.array.ageArray)
        val myAgeAdapterr = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageList)
        myAgeAdapterr.setDropDownViewResource(R.layout.age_spinner)
        binding.ageSpinner.adapter = myAgeAdapterr
        binding.ageSpinner.prompt = "연령대를 선택해주세요"

        val selectedAge : String = binding.ageSpinner.getSelectedItem().toString()

        var userId = binding.setID.text
        var userPw = binding.setPW.text
        var duplicatePw = binding.dupPw.text
        var gender = ""


        binding.signStart.setOnClickListener{

            var id = userId.toString()
            var pw = userPw.toString()
            var dpw = duplicatePw.toString()

            if(!pw.equals(dpw)){
                Toast.makeText(applicationContext, "두 비밀번호가 같지 않습니다.", Toast.LENGTH_LONG).show()
//                Log.d("myTag", "비밀번호 불일치")
            }
            else {
//                Log.d("myTag", "평가이력 체크로 이동")
                //sendUserInfo(userId.toString(), userPw.toString())
                if(id != null && pw != null && gender != null && selectedAge != null){
                    id = userId.toString()
                    pw = userPw.toString()
                    ActivityStart(id, pw, gender, selectedAge)
                }
                else{
                    Toast.makeText(applicationContext, "모든 정보를 입력해주세요.", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.gender.setOnCheckedChangeListener{ _, checkId ->

            when(checkId){
                R.id.female -> {
                    gender = "여자"
                }
                R.id.male -> {
                    gender = "남자"
                }
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

    private fun ActivityStart(userid : String, userpw : String, gender : String, age : String){
        val intent = Intent(this, SigndataActivity::class.java)
        intent.putExtra("info", SignInfo(userid, userpw, gender, age))
        startActivity(intent)
    }


}


