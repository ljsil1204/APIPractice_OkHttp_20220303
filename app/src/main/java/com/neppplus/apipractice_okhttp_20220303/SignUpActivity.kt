package com.neppplus.apipractice_okhttp_20220303

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.neppplus.apipractice_okhttp_20220303.databinding.ActivitySignUpBinding
import com.neppplus.apipractice_okhttp_20220303.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.edtNickname.addTextChangedListener {

            binding.txtNicknameCheckResult.text = "중복 확인을 해주세요."

        }

//        닉네임 검사 버튼 가능.
        binding.btnNicknameCheck.setOnClickListener {

//            입력한 닉네임 가져오기
            val inputNickname = binding.edtNickname.text.toString()

//            ServerUtil 중복체크 만들어놓은 함수 불러내기 -> NICK_NAME
            ServerUtil.getRequestDuplicatedCheck("NICK_NAME", inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

//                    문구변경 -> runOnUiThread안에서 처리
                    runOnUiThread {


                        when(code){

                            200 -> {
                                binding.txtNicknameCheckResult.text = "사용해도 좋은 닉네임 입니다."
                            }
                            else -> {
                                binding.txtNicknameCheckResult.text = "다른 닉네임으로 사용해주세요."
                            }

                        }

                    }

                }

            })

        }


        binding.edtEmail.addTextChangedListener {

//            내용이 한글자라도 바뀌면, 무조건 재검사 요구 문장.

            binding.txtEmailCheckResult.text = "중복 확인을 해주세요."

        }

        binding.btnEmailCheck.setOnClickListener {

//            입력한 이메일 값 추출
            val inputCEmail = binding.edtEmail.text.toString()

//            서버의 중복확인 기능 (/user_check - GET) API 활용 => ServerUtil에 함수 추가, 가져다 활용
//            그 응답 code 값에 따라 다른 문구 배치

            ServerUtil.getRequestDuplicatedCheck("EMAIL", inputCEmail, object : ServerUtil.JsonResponseHandler{

                override fun onResponse(jsonObj: JSONObject) {

//                    code 값에 따라 이메일 사용 가능 여부

                    val code = jsonObj.getInt("code")

                    runOnUiThread {

                        when(code) {
                            200 -> {
                                binding.txtEmailCheckResult.text = "사용해도 좋은 이메일입니다."
                            }
                            else -> {
                                binding.txtEmailCheckResult.text = "다른 이메일로 다시 검사해주세요."
                            }
                        }

                    }


                }

            })

        }


        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNickname,
                object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(jsonObj: JSONObject) {

//                        회원 가입 성공 / 실패 분기

                        val code = jsonObj.getInt("code")

                        if (code == 200){

//                            가입한 사람의 닉네임 추출 > ~~님 , 가입을 축하합니다! 토스트
//                            회원가입 종료 > 로그인 화면 복귀

                            val dataObj = jsonObj.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            
                            val nickname = userObj.getString("nick_name")

                            runOnUiThread {
                                Toast.makeText(mContext, "${nickname}님, 가입을 축하합니다!", Toast.LENGTH_SHORT).show()
                            }

//                            화면 종료 : 객체 소멸 (UI 동작 x)
                            finish()
                            
                        }
                        else {

                            val message = jsonObj.getString("message")

                            runOnUiThread {
                                Toast.makeText(mContext, "실패사유 : ${message}", Toast.LENGTH_SHORT).show()
                            }

                        }

                    }

                }
            )

        }

    }

    override fun setValues() {


    }

}