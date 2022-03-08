package com.neppplus.apipractice_okhttp_20220303

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.apipractice_okhttp_20220303.databinding.ActivityViewTopicDetailBinding
import com.neppplus.apipractice_okhttp_20220303.datas.TopicData
import com.neppplus.apipractice_okhttp_20220303.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding

//    보여주게 될 토론 주제 데이터 > 이벤트 처리, 데이터 표현 등 여러 함수에서 사용
    lateinit var mTopicData : TopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_topic_detail)
        mTopicData = intent.getSerializableExtra("topic") as TopicData
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        btnVote1 클릭 => 첫 진영의 id 값을 찾아서, 거기에 투표
//        서버에 전달 => API 활용.

        binding.btnVote1.setOnClickListener {

//            서버의 투표 API 호출
            ServerUtil.postRequestVote(mContext, mTopicData.sideList[0].id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

//                    토스트로, 서버가 알려준 현재 상황 (신규투표 or 재투표 or 취소 등)
                    val message = jsonObj.getString("message")

                    runOnUiThread {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

//                    변경된 득표현황을 다시 불러오자.
                    getTopicDetailFromServer()

                }
            })

//            투표 현황 새로고침 (응답)
        }


//        2번 진영 선택시, 그 진영에 투표하기 (연습문제)
        binding.btnVote2.setOnClickListener {

            ServerUtil.postRequestVote(mContext, mTopicData.sideList[1].id, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val message = jsonObj.getString("message")

                    runOnUiThread {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

                    getTopicDetailFromServer()

                }

            })

        }


    }

    override fun setValues() {

        setTopicDataToUi()
        getTopicDetailFromServer()

    }

    fun setTopicDataToUi(){

//        토론 주제에 대한 데이터들은, UI에 반영하는 함수
//        화면 초기 진입 실행 + 서버에서 다시 받아왔을 때도 실행.

        binding.txtTitle.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopicBackground)

//        1번 진영 제목, 2번 진영 제목
        binding.txtSide1.text = mTopicData.sideList[0].title
        binding.txtSide2.text = mTopicData.sideList[1].title

//        1번진영 득표수, 2번진영 득표수
        binding.txtVoteCount1.text = "${mTopicData.sideList[0].voteCount}표"
        binding.txtVoteCount2.text = "${mTopicData.sideList[1].voteCount}표"

    }

    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                토론 정보 JSONObject (topicObj) => TopicData() 형태로 변환 (여러 화면에서 진행, 함수로 만들어두자.)
                val topicData = TopicData.getTopicDataFromJson(topicObj)

//                변환된 객체를, mTopicData로 다시 대입 => UI 반영도 다시 실행
                mTopicData = topicData

                runOnUiThread {
                    setTopicDataToUi()
                }

            }

        })

    }

}