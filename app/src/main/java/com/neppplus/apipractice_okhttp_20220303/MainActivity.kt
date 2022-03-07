package com.neppplus.apipractice_okhttp_20220303

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

//                JSONArray는 for-each 문법 지원 x. (차후 : ArrayList의 for-each 활용 예정)
//                JAVA : for (int i=0; i< 배열.length; i++)
                for ( i in 0 until topicsArr.length()) {

//                    []  => {}, {}, {} ,... 순서에 맞는 {}를 변수에 담자
//                    JSON 파싱의  {} => (JSONArray에게서) JSONObject 추출.
                    val topicObj = topicsArr.getJSONObject(i)

                    Log.d("받아낸 주제", topicObj.toString())

                }

            }

        })

    }

}