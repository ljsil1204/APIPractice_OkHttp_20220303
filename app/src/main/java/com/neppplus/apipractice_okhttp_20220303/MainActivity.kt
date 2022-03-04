package com.neppplus.apipractice_okhttp_20220303

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.apipractice_okhttp_20220303.databinding.ActivityMainBinding
import com.neppplus.apipractice_okhttp_20220303.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnLogin.setOnClickListener {

//            id / pw 추출
            val inputId = binding.edtId.text.toString()
            val inputPw = binding.edtPassword.text.toString()

//            API 서버에 아이디 / 비번을 보내서 실제로 회원인지 검사 => 로그인 시도
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler{
                
                override fun onResponse(jsonObj: JSONObject) {
                    
//                    화면의 입장에서, 로그인 결과를 받아서 처리할 코드.
//                    서버에 다녀오고 실행 : 라이브러리가 자동으로 백그라운드에서 돌도록 만들어둔 코드
                    
                    val code = jsonObj.getInt("code")
                    
                    if (code == 200) {

                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        }
                        
                    }
                    else{

                        val message = jsonObj.getString("message")

//                        토스트 : UI 조작 => 백그라운드에서 UI를 건드리면, 위험동작으로 간주하고 앱을 강제 종료.

                        runOnUiThread{
//                            토스트를 띄우는 코드만, UI 전담 쓰레드에서 실행하도록.
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()

                        }

                    }
                    
                }

            })


        }
    }

    override fun setValues() {

    }

}