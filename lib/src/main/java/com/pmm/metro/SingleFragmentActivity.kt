package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:17
 * Description:
 */

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.weimu.universalview.core.activity.BaseActivity

/**
 * 专门显示fragment的activity的基类
 */
internal class SingleFragmentActivity : BaseActivity() {
    protected var fragmentManager = supportFragmentManager

    companion object {
        var fragment: Fragment? = null
    }

    override fun getLayoutUI(): ViewGroup? = BaseFragmentUI(this)

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fragment == null) return
        fragmentManager.beginTransaction().add(R.id.single_fragment_container, fragment!!).commitAllowingStateLoss()
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
        orientation = LinearLayout.VERTICAL

        val frameLayout = FrameLayout(context)
        frameLayout.id = R.id.single_fragment_container

        addView(frameLayout)

        val targetLayoutParams = (frameLayout.layoutParams as LinearLayoutCompat.LayoutParams).apply {
            width = LinearLayoutCompat.LayoutParams.MATCH_PARENT
            height = LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        }
        frameLayout.layoutParams = targetLayoutParams
    }


}