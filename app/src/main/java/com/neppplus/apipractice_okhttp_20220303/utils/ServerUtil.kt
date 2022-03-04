package com.neppplus.apipractice_okhttp_20220303.utils

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

        }

    }


}