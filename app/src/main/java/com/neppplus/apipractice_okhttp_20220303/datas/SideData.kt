package com.neppplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject
import java.io.Serializable

// 토론의 선택 진영 (이름, id 값 등등)을 표현

class SideData : Serializable {

    var id = 0
    var title = ""
    var voteCount = 0

    companion object{

        fun getSideDataFromJson( jsonObj : JSONObject) : SideData{

            val sideData = SideData()

            sideData.id = jsonObj.getInt("id")
            sideData.title = jsonObj.getString("title")
            sideData.voteCount = jsonObj.getInt("vote_count")

            return sideData
        }

    }

}