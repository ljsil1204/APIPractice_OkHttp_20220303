package com.neppplus.apipractice_okhttp_20220303

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.apipractice_okhttp_20220303.databinding.ActivityMainBinding
import com.neppplus.apipractice_okhttp_20220303.datas.TopicData
import com.neppplus.apipractice_okhttp_20220303.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

//    실제로 서버가 내려주는 주게 목록을 담을 그릇
    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        메인 화면 정보 가져오기 => API 호출  / 응답 처리
        getTopicListFromServer()

    }

    fun getTopicListFromServer() {

        ServerUtil.getRequestMainInfo(mContext, object  : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

//                서버가 주는 토론 주제 목록 파싱 => mTopicList 추가

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")
                
//                topicsArr 내부를 하나씩 추출 (JSONObject {} ) => TopicData()로 변환

            }

        })

    }

}