package com.pmm.metro.demo

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pmm.metro.Metro
import com.pmm.metro.Station
import com.pmm.ui.ktx.setOnClickListenerPro
import com.pmm.ui.ktx.toast
import kotlinx.android.synthetic.main.activity_a.*

@Station("/a")
class AActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val url = intent.getStringExtra("url")
        toast(
            """
            name = $name
            age = $age
            url = $url
        """.trimIndent()
        )

        mTvJump.setOnClickListenerPro {
            Metro.with(this).path("/b").go()
        }

        mBtnBack.setOnClickListenerPro {
            setResult(Activity.RESULT_OK)
            onBackPressed()
        }
    }
}
