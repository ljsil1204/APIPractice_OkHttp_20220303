package com.neppplus.apipractice_okhttp_20220303.datas

import java.io.Serializable

class TopicData : Serializable {

    var id = 0 // id는 Int라고 명시.
    var title = "" // title은 String이라고 명시
    var imageURL = "" // 서버 : img_url, 앱 : imageRUL 변수명 다른 경우
    var replyCount = 0

}