package com.neppplus.apipractice_okhttp_20220303.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

        return row

    }

}