package com.neppplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject
import java.io.Serializable

class TopicData : Serializable {

    var id = 0 // id는 Int라고 명시.
    var title = "" // title은 String이라고 명시
    var imageURL = "" // 서버 : img_url, 앱 : imageRUL 변수명 다른 경우
    var replyCount = 0

    companion object{

//    주제 정보를 담고 있는 JSONObject 가 들어오면 > TopicData형태로 변환해주는 함수. => static 메쏘드

        fun getTopicDataFromJson( jsonObj : JSONObject ) : TopicData {

//            기본 내용의 TopicData 생성
            val topicData = TopicData()
            topicData.id = jsonObj.getInt("id")
            topicData.title = jsonObj.getString("title")
            topicData.imageURL = jsonObj.getString("img_url")
            topicData.replyCount = jsonObj.getInt("reply_count")

//            jsonObj에서 데이터 추출 > 멤버변수 대입

//            완성된 TopicData 리턴
            return topicData

        }

    }


}