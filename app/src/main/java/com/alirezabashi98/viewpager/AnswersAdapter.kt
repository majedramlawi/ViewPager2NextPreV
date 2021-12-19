package com.alirezabashi98.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnswersAdapter(val context: Context, val answers: List<Answer>, val itemClick: (Answer, View?) -> Unit) : RecyclerView.Adapter<AnswersAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.answer_item, parent, false)

        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return answers.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        answers[position]?.let { holder.bindProduct( position, it) }
    }

    fun selectAll() {

        notifyDataSetChanged()
    }

    inner class Holder(itemView: View?, val itemClick: (Answer, View?) -> Unit): RecyclerView.ViewHolder(itemView!!) {

        val tv_answer=itemView!!.findViewById<TextView>(R.id.tv_answer)



        fun bindProduct(position: Int, answer:Answer){

            tv_answer.text=answer.answer

            itemView.setOnClickListener { itemClick(answer,itemView) }
        }
    }
}

