package com.example.chatappfirebase.SplachSreen.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import android.content.SharedPreferences
import com.example.chatappfirebase.SplachSreen.adpter.OnBoardingAdapter
import com.example.chatappfirebase.SplachSreen.model.OnBoardingIemModel
import com.example.chatappfirebase.R
import com.example.chatappfirebase.Login.SignUpActivity


class MainActivity : AppCompatActivity() {

    var adapter: OnBoardingAdapter? = null
    var list: ArrayList<OnBoardingIemModel>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list!!.add(
            OnBoardingIemModel(
                R.drawable.one,
                "Order Your Food",
                "Now you can order food any time right from your mobile"
            )
        )

        list!!.add(
            OnBoardingIemModel(
                R.drawable.two,
                "Cooking Safe Food",
                "We are maintain safty and we keep clean while making food"
            )
        )

        list!!.add(
            OnBoardingIemModel(
                R.drawable.three,
                "Quick Delivery",
                "Order your favorite meals will be immediately deliver"
            )
        )

        adapter = OnBoardingAdapter(list!!)
        var viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapter
        var wormIndicator = findViewById<WormDotsIndicator>(R.id.indicator)
        wormIndicator.setViewPager2(viewPager)
        var btnNext = findViewById<Button>(R.id.btn_next)
        var btnSkip = findViewById<TextView>(R.id.tv_skip)
        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < adapter!!.itemCount) {
                viewPager.currentItem += 1
            } else {
                var intent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnSkip.setOnClickListener {
            var intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        val pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)
        if (pref.getBoolean("activity_executed", false)) {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val ed: SharedPreferences.Editor = pref.edit()
            ed.putBoolean("activity_executed", true)
            ed.commit()
        }
    }
}