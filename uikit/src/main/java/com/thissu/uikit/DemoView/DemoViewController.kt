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
        blueview!!.frame = CGRect(13f, 13f, 130f, 130f)
        this.view!!.addSubview(blueview!!)
        blueview!!.backgroundColor = Color.BLUE

        yellowview = UIView()
        yellowview.frame = blueview!!.bounds().insetBy(10f,10f)
        yellowview.backgroundColor = Color.YELLOW
        blueview!!.addSubview(yellowview)

        //下面添加8个子视图，测试动画效率
        val tempv1 = UIView()
        tempv1.frame = yellowview.bounds().insetBy(10f,10f)
        tempv1.backgroundColor = Color.RED
        yellowview.addSubview(tempv1)

        val tempv2 = UIView()
        tempv2.frame = tempv1.bounds().insetBy(10f,10f)
        tempv2.backgroundColor = Color.BLUE
        tempv1.addSubview(tempv2)

        val tempv3 = UIView()
        tempv3.frame = tempv2.bounds().insetBy(10f,10f)
        tempv3.backgroundColor = Color.YELLOW
        tempv2.addSubview(tempv3)




        var label = UILabel()
        label.frame = CGRect(13f,156f,294f,30f)
        label.backgroundColor = Color.GREEN
        label.text = "Hello UIKit！"

        view!!.addSubview(label)

        touchView = DemoView()
        touchView.frame = CGRect(50f,199f,220f,40f)
        touchView.backgroundColor = Color.RED
        touchView.layer.borderColor = Color.YELLOW
        touchView.layer.cornerRadius = 5f
        touchView.layer.borderWidth = 2f
        this.view!!.addSubview(touchView)


        touchView.touchblock = {

            moveBlueView()
        }

    }

    override fun viewWillAppear() {
        super.viewWillAppear()
        //
        NSLog.print("demo viewController viewWillAppear")
    }


    fun moveBlueView(){

        blueview?.animation()


    }
}