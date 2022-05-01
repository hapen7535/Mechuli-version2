package com.example.version2mechuli

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version2mechuli.databinding.ActivitySignupBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding
    private val idch by lazy { findViewById<EditText>(R.id.setID)}
    private val checkPoint by lazy { findViewById<TextView>(R.id.checkpoint) }
    private val callList = mutableListOf<Call<*>>()
    var check : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userId = binding.setID.text
        var userPw = binding.setPW.text
        var duplicatePw = binding.dupPw.text

        /*
        binding.dupChk.setOnClickListener {
            var id = binding.setID.text.toString()


                /*
                //중복되는 아이디라면
                if (check == false) {
                    if(binding.noneDup.visibility == View.INVISIBLE) {
                        binding.dupText.visibility = View.VISIBLE
                    }
                    binding.signStart.isEnabled = false
                }

                //중복되지 않는 아이디라면
                if (check == true) {
                    if(binding.dupText.visibility == View.INVISIBLE) {
                        binding.noneDup.visibility = View.VISIBLE
                    }
                    binding.signStart.isEnabled = true
                }

                 */


        }*/

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

    fun dupId(view : View) {
        val id = idch.text.toString()
        val call = InfoClient.service.requestData(id)
        call.enqueue(object : Callback<GetIdresult>{
            override fun onFailure(call: Call<GetIdresult>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "통신 실패 : " + t.message,
                    Toast.LENGTH_LONG
                    ).show()
                Log.d("myTag", (t.message.toString()))
            }

            override fun onResponse(call: Call<GetIdresult>, response: Response<GetIdresult>) {
                val body = response.body()
                if(body != null && response.isSuccessful){
                    checkPoint.text = Gson().toJson(body)
                }
            }
        })
        callList.add(call)

    }

    override fun onDestroy() { //Activity 종료 후 네트워크 요청 취소
        callList.forEach { it.cancel() }
        super.onDestroy()
    }

    suspend fun checkId(id: String) : Boolean {
        var gson = GsonBuilder().setLenient().create()
        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
            .baseUrl("http://10.0.2.2:3333/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱등록
            .build()

//        return suspendCancellableCoroutine { cont ->
            if (id != null) {

                //            Toast.makeText(applicationContext, "id : " + id + ", pw : " + pw, Toast.LENGTH_LONG).show()
                var sendUserid: SendUserId = retrofit.create(SendUserId::class.java) //아이디 전송

                sendUserid.requestData(id).enqueue(object : Callback<GetIdresult> {
                    override fun onFailure(call: Call<GetIdresult>, t: Throwable) { //통신 실패
                        Toast.makeText(
                            applicationContext,
                            "통신 실패 : " + t.message,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.d("myTag", (t.message.toString()))
//                        cont.resumeWithException(t)

                    }

                    override fun onResponse(
                        call: Call<GetIdresult>,
                        response: Response<GetIdresult>
                    ) { //통신 성공
                        if (response.isSuccessful) {
                            var dataclass = response.body() //중복되는지 결과
                            check = dataclass!!.isRedup
                            Log.d("Idcheck", check.toString())
//                            cont.resume(response.body()!!.isRedup)
                        } else {
//                            cont.resumeWithException(Exception("Received error response : ${response.message()}"))
                        }

                    }

                })


            }

        return check

    }


    private fun sendUserInfo(id : String, pw : String) {

        val intent = Intent(this, SigndataActivity::class.java)

        var gson = GsonBuilder().setLenient().create()

        var retrofit = Retrofit.Builder() //레트로핏 인스턴스 생성
            .baseUrl("http://10.0.2.2:3333/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱등록
            .build()


        if(id != null && pw != null ) {

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


