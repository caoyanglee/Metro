package com.pmm.metro.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.pmm.metro.R
import com.weimu.universalview.core.activity.BaseActivity

/**
 * Author:你需要一台永动机
 * Date:2019-06-15 15:20
 * Description:专门显示fragment的activity的基类
 */
internal class FragmentActivity : BaseActivity() {

    companion object {
        var fragment: Fragment? = null
    }

    override fun getLayoutUI(): ViewGroup? = BaseFragmentUI(this)

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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