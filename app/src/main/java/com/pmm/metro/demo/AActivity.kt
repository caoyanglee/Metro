package com.pmm.metro.demo

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pmm.metro.Metro
import com.pmm.metro.Station
import com.weimu.universalview.ktx.setOnClickListenerPro
import com.weimu.universalview.ktx.toast
import kotlinx.android.synthetic.main.activity_a.*

@Station("/a")
class AActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        toast("""
            name = $name
            age = $age
        """.trimIndent())

        mTvJump.setOnClickListenerPro {
            Metro.with(this).path("/b").go()
        }

        mBtnBack.setOnClickListenerPro {
            setResult(Activity.RESULT_OK)
            onBackPressed()
        }
    }
}
