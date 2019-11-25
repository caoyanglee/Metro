package com.pmm.metro.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pmm.metro.Metro
import com.pmm.metro.annotatoin.Station
import com.pmm.ui.ktx.click
import kotlinx.android.synthetic.main.activity_b.*

@Station("/b")
class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        mTvJump.click {
            Metro.with(this)
                .path("/main")
                .overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.noting)
                .go()
        }
    }
}
