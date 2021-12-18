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
        castView()
        addToList()

        viewPager.adapter = ViewPagerAdapter(data, this)
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

    @SuppressLint("SetTextI18n")
    override fun condition(position: Int, fullSize: Int) {



        Log.e("position",position.toString())
        Log.e("fullSize",fullSize.toString())
        //Toast.makeText(this, "$position / $fullSize", Toast.LENGTH_SHORT).show()

    }


}