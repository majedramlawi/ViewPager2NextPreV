package com.alirezabashi98.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val context: Context, private val dataValue: List<Question>, private val condition: ConditionViewPager) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun getItemCount(): Int = dataValue.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        condition.condition(position,dataValue.size)
        dataValue[position].let {
            holder.bindProduct( it)
        }
    }

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /**/
        val rv_answers: RecyclerView = view.findViewById(R.id.rv_answers)
        val txt: TextView = view.findViewById(R.id.tv_question)

        fun bindProduct(question: Question){
            txt.text=question.question

            val isSingleSelect=true
            val selectedAnswer = mutableListOf<Answer>()

            val adapter = AnswersAdapter(context, question.answers){ answer, itemView ->
                val tv_answer=itemView!!.findViewById<TextView>(R.id.tv_answer)

                if(selectedAnswer.contains(answer)) {
                    if(!isSingleSelect){
                        selectedAnswer.remove(answer)
                        tv_answer.setTextColor(ContextCompat.getColor(context,R.color.black))
                    }

                }else{
                    selectedAnswer.add(answer)
                    tv_answer.setTextColor(ContextCompat.getColor(context,R.color.purple_700))
                }

                //Log.e("answer",answer.toString())

            }
            //adapter.selectAll()

            val layoutManager = GridLayoutManager(context,1, LinearLayoutManager.VERTICAL,false)

            rv_answers.layoutManager = layoutManager
            rv_answers.adapter = adapter



        }

    }

}

interface ConditionViewPager {

    fun condition(position : Int, fullSize : Int)
}