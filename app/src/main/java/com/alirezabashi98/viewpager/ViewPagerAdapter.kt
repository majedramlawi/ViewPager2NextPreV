package com.alirezabashi98.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val dataValue: List<String>, private val condition: ConditionViewPager) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun getItemCount(): Int = dataValue.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        condition.condition(position,dataValue.size)
        dataValue[position].let { holder.bindProduct( it) }
    }

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

       val txt: TextView = view.findViewById(R.id.textView)

        fun bindProduct(data:String){
            txt.text=data
        }

    }

}

interface ConditionViewPager {

    fun condition(position : Int, fullSize : Int)

}