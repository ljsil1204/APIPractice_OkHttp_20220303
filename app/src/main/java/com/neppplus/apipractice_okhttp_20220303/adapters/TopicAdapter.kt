package com.neppplus.apipractice_okhttp_20220303.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.apipractice_okhttp_20220303.R
import com.neppplus.apipractice_okhttp_20220303.datas.TopicData

class TopicAdapter(
    val mContext : Context,
    resId : Int,
    val mList : List<TopicData>
) : ArrayAdapter<TopicData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]

        val txtTitle = row.findViewById<TextView>(R.id.txtTitle)
        val imgTopicBackground = row.findViewById<ImageView>(R.id.imgTopicBackground)
        val txtReplyCount = row.findViewById<TextView>(R.id.txtReplyCount)

        txtTitle.text = data.title

//        data > 서버에서 준 주제 데이터
//        imageURL 변수 파싱 => 이미지 인터넷 주소
//        웹에 있는 이미지 > 이미지 뷰에 적용 > Glide 라이브러리

        Glide.with(mContext).load(data.imageURL).into(imgTopicBackground)

        txtReplyCount.text = "${data.replyCount}명 참여중!"



        return row

    }

}