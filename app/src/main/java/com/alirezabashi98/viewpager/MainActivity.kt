package com.alirezabashi98.viewpager

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity(), ConditionViewPager {

    private val data = mutableListOf<String>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val question = mutableListOf<Question>()
        val answersA = mutableListOf<Answer>()
        val answersB = mutableListOf<Answer>()
        //question(1,"What is Your name",0,answersA)

        answersA.add(Answer(1,"A",true))
        answersA.add(Answer(2,"B",false))
        answersA.add(Answer(3,"C",false))
        answersA.add(Answer(4,"D",false))
        //Log.e("comment_postCD",answersA.toString())
        answersB.add(Answer(1,"AX",true))
        answersB.add(Answer(2,"BX",false))
        answersB.add(Answer(3,"CX",true))
        answersB.add(Answer(4,"DX",false))

        question.add(Question(1,"What is A",false,answersA))

        question.add(Question(2,"What is B",true,answersB))

        question.add(Question(3,"What is C",true,answersA))

        castView()
        addToList()

        viewPager.adapter = ViewPagerAdapter(this,question, this)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(viewPager)

        btnNext.setOnClickListener {

            val currPos: Int = viewPager.currentItem
            if ((currPos + 1) != data.size) {
                viewPager.currentItem = currPos + 1
            }

            if (currPos+1 == data.size) {
                btnNext.text = "Submit"
            }else
                btnNext.text = "Next"

        }

        btnBack.setOnClickListener {

            val currPos: Int = viewPager.currentItem
            if (currPos != 0) {
                viewPager.currentItem = currPos - 1
            }
        }

    }

    private fun castView() {

        viewPager = findViewById(R.id.view_pager2)
        indicator = findViewById(R.id.indicator)
        btnNext = findViewById(R.id.btn_next)
        btnBack = findViewById(R.id.btn_back)

    }

    private fun addToList() {
        for (item in 1..10) {
            data.add("Question $item")
        }
    }


    override fun condition(position: Int, fullSize: Int) {

        Log.e("position",position.toString())
        Log.e("fullSize",fullSize.toString())
        //Toast.makeText(this, "$position / $fullSize", Toast.LENGTH_SHORT).show()

    }
}

data class Question(val id:Int,
                    val question:String,
                    val is_multi:Boolean,
                    val answers:List<Answer>
)

data class Answer(val id:Int,
                  val answer:String,
                  val is_correct: Boolean)