package com.thissu.uikit

import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem
import com.thissu.uikit.Foundation.NSLog


/**
 * Created by apple on 2017/10/10.
 *
 * UIView的动画接口
 */

/**
 * 动画接口
 * @param duration:持续时间
 * @param delay：延迟多久执行
 * @param animations：要执行的动画block
 * @param completion：完成后执行的动作
 */


 fun com.thissu.uikit.UIView.animation(duration: Float = 0f, delay: Float = 0f, animations:()->Unit = {}, completion:()->Unit = {}){


    val originX = frame.x
    val mSpringSystem = SpringSystem.create()
    val spring = mSpringSystem
            .createSpring()
            .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50.0, 7.0))
            .addListener(object : SimpleSpringListener() {
                override fun onSpringUpdate(spring: Spring) {
                    val value = spring.currentValue

                    frame.x = originX + value.toFloat()
                    NSLog.print("frame x:${frame.x}")

                    setNeedsDisplay()

                }
            }).setEndValue(100.0)

}
