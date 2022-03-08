package com.neppplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject

class UserData {

    var id = 0
    var email = ""
    var nickname = ""

    companion object{

        fun getUserDataFromServer ( jsonObj : JSONObject) :UserData {

            val userData = UserData()

            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickname = jsonObj.getString("nick_name")

            return userData
        }

    }

}