package com.pmm.metro.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pmm.metro.Metro
import com.pmm.metro.Station
import com.weimu.universalview.ktx.setOnClickListenerPro
import kotlinx.android.synthetic.main.activity_b.*

@Station("/b")
class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        mTvJump.setOnClickListenerPro {
            Metro.with(this)
                .path("/a")
                .overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.noting)
                .go()
        }
    }
}
