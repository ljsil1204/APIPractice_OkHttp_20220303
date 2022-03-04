package com.neppplus.apipractice_okhttp_20220303.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    서버에 Request를 날리는 역할
//    함수를 만들려고 하는데, 어떤 객체가 실행해도 결과만 잘 나오면 그만인 함수
//    코틀린에서 static에 해당하는 개념?  companion object { } 에 만들자.

    companion object {

//        서버 컴퓨터 주소만 변수로 저장 (관리 일원화) => 외부 노출 x
        private val BASE_URL = "http://54.180.52.26"

//        로그인 기능 호출 함수

        fun postRequestLogin ( id : String, pw : String ) {

//            Request 제작 -> 실제 호출 -> 서버용 응답을, 화면에 전달

//            제작 1) 어느 주소 (url) 로 접근할지? => 서버주소 + 기능 주소
            val urlString = "${BASE_URL}/user"

//            제작 2) 파라미터 담아주기 => 어떤 이름표 / 어느 공간에
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            제작 3) 모든 Request 정보를 종합한 객체 생성. (어느 주소로 + 어느 메쏘드 + 어떤 파라미터를)
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

//            종합한  Request도 실제 호출을 해줘야 API 호출이 실행됨. (startActivity 같은  동작 필요함)
//            실제 호출 : 클라이언트로써 동작 > OKHttpClient 클래스
            val client = OkHttpClient()

//            OkHttpClient 객체를 이용 > 서버에 로그인 기능 실제 호출
//            호출을 했으면, 서버가 수행한 결과를 받아서 처리.
//            => 서버에 다녀와서 할일을 등록 : enqueue(callback)

            client.newCall(request).enqueue( object : Callback {
                override fun onFailure(call: Call, e: IOException) {

//                    실패 : 서버 연결 자체를 실패. 응답이 오지 않았다.
//                    ex. 인터넷 끊김, 서버 접속 불가 등등 물리적 연결 실패
//                    ex. 비번 틀려서 로그인 실패 : 서버 연결 성공, 응답도 돌아왔는데 > 그 내용만 실패 (물리적 실패 x)

                }

                override fun onResponse(call: Call, response: Response) {

//                    어떤 내용이던, 응답 자체는 잘 돌아온 경우 (그 내용은 성공/실패 일 수 있다)

//                    응답 : response 변수 > 응답의 본문 (body) 만 보자

                    val bodyString = response.body!!.string() // toString으로 하면 안됨! , string() 기능은 1회용 -> 변수에 담아두고 이용.

//                    응답의 본문을 String으로 변환하면, JSON Encoding 적용된 상태 (한글 깨짐)
//                    JSONObject 객체로 응답 본문 String 을 변환해주면, 한글이 복구됨.
//                      => UI에서도 JSONObject를 이용해서, 데이터 추출 / 실제 활용

                    val jsonObj = JSONObject( bodyString )

                    Log.d("서버 테스트", jsonObj.toString())

                }

            })

        }

    }


}