package com.neppplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject
import java.util.*

class ReplyData(
    var id : Int,
    var content : String,
){

    var writer = UserData() //모든 댓글에는 작성자가 있다. null 가능성이 없다.

    var selectedSide = SideData() // 모든 댓글에는 선택한 진영이 있다. null 가능성 x.

//    일/시 데이터를 변경 => 내부의 숫자만 변경. 변수에 새 객체 대입 x => val 써도 됨

    val createdAt = Calendar.getInstance()

//    보조생성자 추가 연습 : 파라미터 x.
    constructor() : this(0, "내용없음")
    
    companion object {

        fun getReplyDataFromJson ( jsonObj : JSONObject ) : ReplyData {

            val replyData = ReplyData()

            replyData.id = jsonObj.getInt("id")
            replyData.content = jsonObj.getString("content")
            replyData.writer = UserData.getUserDataFromServer( jsonObj.getJSONObject("user") )

            replyData.selectedSide = SideData.getSideDataFromJson( jsonObj.getJSONObject("selected_side") )

//            Calendar로 되어있는 작성일시의 시간을, 서버가 알려주는 댓글 작성 일시로 맞춰줘야함.

//            임시1)2022-01-12 10:55:35 로 변경 (한번에 모두 변경)
            replyData.createdAt.set( 2022, 1, 12, 10, 55, 35  )

//            임시2) 연도만 2021년으로 변경. (항목을 찍어서 변경)
            replyData.createdAt.set(Calendar.YEAR, 2021)

            return replyData

        }

    }

}