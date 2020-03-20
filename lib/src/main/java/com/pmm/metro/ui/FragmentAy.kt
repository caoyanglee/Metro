package com.pmm.metro.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.pmm.metro.R

/**
 * Author:你需要一台永动机
 * Date:2019-06-15 15:20
 * Description:专门显示fragment的activity的基类
 */
internal class FragmentAy : AppCompatActivity() {

    companion object {
        var fragment: Fragment? = null
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(BaseFragmentUI(this))

        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.single_fragment_container, fragment!!)
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragment = null
    }
}

class BaseFragmentUI : LinearLayoutCompat {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        this.apply {
            this.orientation = VERTICAL
        }

        FrameLayout(context).apply {
            this.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            this.id = R.id.single_fragment_container
            this@BaseFragmentUI.addView(this)
        }


    }
}