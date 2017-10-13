package com.thissu.uikit.DemoView

import android.graphics.Color
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIViewController.UIViewController

/**
 * Created by apple on 2017/10/13.
 * 测试触摸的vc
 */
class DemoTouchViewController() : UIViewController(){

    var touchview1:DemoTouchView? = null

    var touchview2:DemoTouchView? = null

    override fun viewDidLoad() {
        touchview1 = DemoTouchView()
        touchview1!!.frame = CGRect(13f,100f,200f, 40f)
        touchview1!!.backgroundColor = Color.RED
        this.view!!.addSubview(touchview1!!)

        touchview2 = DemoTouchView()
        touchview2!!.frame = CGRect(13f,250f,200f, 40f)
        touchview2!!.backgroundColor = Color.GREEN
        this.view!!.addSubview(touchview2!!)
    }
}