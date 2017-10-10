package com.thissu.uikit.DemoView

import android.graphics.Color
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIViewController.UIViewController
import com.thissu.uikit.UIView
import android.os.CountDownTimer
import android.util.Log
import com.thissu.uikit.UIAppDelegate
import com.thissu.uikit.UIApplication.UIApplication
import com.thissu.uikit.UILabel.UILabel
import android.view.animation.Transformation
import android.view.animation.Animation
import com.thissu.uikit.animation


/**
 * Created by this on 2017/10/3.
 */
class DemoViewController(): UIViewController() {

    //在声明子视图变量的时候一定要用:UIView? = null的方式，否则会出现block中变量被值拷贝从而修改后不生效的问题。
    var blueview:UIView? = null

    //下面两个变量的声明方式会出问题，切记。！！！！！
    var yellowview = UIView()
    var touchView = DemoView()


    var isMoving = false


    override fun viewDidLoad(){
        NSLog.print("demo viewController viewDidLoad")
        this.view!!.backgroundColor = Color.WHITE


        //
        blueview = UIView()
        blueview!!.frame = CGRect(x = 100f, y = 100f, width = 130f, height = 140f)
        this.view!!.addSubview(blueview!!)
        blueview!!.backgroundColor = Color.BLUE

        yellowview = UIView()
        yellowview.frame = CGRect(10f,10f,40f,40f)
        yellowview.backgroundColor = Color.YELLOW
        blueview!!.addSubview(yellowview)

        touchView = DemoView()
        touchView.frame = CGRect(110f,400f,100f,100f)
        touchView.backgroundColor = Color.RED
        touchView.layer.borderColor = Color.YELLOW
        touchView.layer.cornerRadius = 20f
        touchView.layer.borderWidth = 6f
        this.view!!.addSubview(touchView)


        touchView.touchblock = {

            moveBlueView()
        }

        var label = UILabel()
        label.frame = CGRect(10f,250f,700f,50f)
        label.backgroundColor = Color.GREEN
        label.text = "Hello UIKit！"

        view!!.addSubview(label)

    }

    override fun viewWillAppear() {
        super.viewWillAppear()
        //
        NSLog.print("demo viewController viewWillAppear")
    }

    override fun viewDidAppear() {
        NSLog.print("demo viewController viewDidAppear")

    }

    fun moveBlueView(){


        blueview?.animation()

        return

        if(!isMoving){
            val cdt = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    var tempframe = blueview!!.frame
                    tempframe.x = tempframe.x + 20f
                    blueview!!.frame = tempframe

                    UIApplication.sharedApplication.keyWindow!!.invalidate()

                    NSLog.print("blue view:$blueview!!")
                    NSLog.print("subviews :${view!!.subViews}")
                    isMoving = true
                }

                override fun onFinish() {
                    isMoving = false

                }
            }

            cdt.start()
        }

    }
}