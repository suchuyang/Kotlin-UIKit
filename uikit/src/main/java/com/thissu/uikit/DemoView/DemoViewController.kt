package com.thissu.uikit.DemoView

import android.graphics.Color
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIViewController.UIViewController
import com.thissu.uikit.UIView
import android.os.CountDownTimer
import com.thissu.uikit.UIApplication.UIApplication


/**
 * Created by this on 2017/10/3.
 */
class DemoViewController(): UIViewController() {

    var aview = UIView()
    var touchView = DemoView()


    override fun viewDidLoad(){
        NSLog.print("demo viewController viewDidLoad")

        NSLog.print("touch view:$touchView")
        //
        aview = UIView()
        aview.frame = CGRect(x = 100f, y = 100f, width = 130f, height = 140f)
        this.view!!.addSubview(aview)
        aview.frame.width = 77f

        NSLog.print("aview is : $aview")
        NSLog.print("subviews is : ${this.view!!.subViews}")

        this.view!!.backgroundColor = Color.WHITE
        aview.backgroundColor = Color.BLUE

        val thirdview = UIView()
        thirdview.frame = CGRect(10f,10f,40f,40f)
        thirdview.backgroundColor = Color.YELLOW
        aview.addSubview(thirdview)

        touchView = DemoView()
        touchView.frame = CGRect(10f,400f,50f,50f)
        touchView.backgroundColor = Color.RED
        this.view!!.addSubview(touchView)

    }

    override fun viewWillAppear() {
        super.viewWillAppear()
        //
        NSLog.print("demo viewController viewWillAppear")
    }

    override fun viewDidAppear() {
        NSLog.print("demo viewController viewDidAppear")

    }
}