package com.thissu.uikit.GoreGraphics

import com.thissu.uikit.Foundation.ERROR_HIGH_GRADE
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UIScreen

/**
 * Created by apple on 2017/9/29.
 *
 * 因为kotlin 没有struct类型，所以只能用类来表示CGRect。而类在赋值的时候是指针赋值，所以大家一定要注意，在自定义的视图里，frame一定要使用copy赋值
 */

data class CGPoint(var x: Float = 0f, var y: Float = 0f){


}

class CGRect(){

    //在每次修改frame的属性的时候，都要同步修改对应的draw尺寸
    var x: Float = 0f
        set(value) {
            field = value
            dpiX = value * frameRatio
        }

    var y: Float = 0f
        set(value) {
            field = value
            dpiY = value * frameRatio
        }

    var width: Float = 0f
        set(value) {
            field = value
            dpiWidth = value * frameRatio
        }

    var height: Float = 0f
        set(value) {
            field = value
            dpiHeight = value * frameRatio
        }

    var frameRatio: Float = 0f//frame的比例，这个比例是真实屏幕分辨率和320的比例

    //dpi尺寸，即真实分辨率的尺寸，在draw的时候一定要使用这个值。
    var dpiX: Float = 0f
    var dpiY: Float = 0f
    var dpiWidth: Float = 0f
    var dpiHeight: Float = 0f



    init {

        //初始化里面要把比例赋值，注意如果frame的初始化在UIScreen之前执行了，那么比例就会出错。所以一定要做赋值校验。
        frameRatio = UIScreen.shared.frameRatio

        if (frameRatio == 0f){
            NSLog.print("$ERROR_HIGH_GRADE 屏幕比例为0，是不是创建frame太着急了？暂时改成1，显示错误时就来这里打断点")
            frameRatio = 1f
        }
    }

    constructor(frameX: Float = 0f, frameY: Float = 0f, frameWidth: Float = 0f, frameHeight: Float = 0f):this(){
        this.x = frameX
        this.y = frameY
        this.width = frameWidth
        this.height = frameHeight
    }

    //
    override fun toString(): String {
        return "(x=${x}, y=${y}, width=${width}, height=${height})"
    }

    //操作符重载，判断是否相等
//    operator override fun  equals(other: Any?): Boolean{
//        return  true
//    }

    fun copy(): CGRect{

        var newRect = CGRect()

        newRect.x = x
        newRect.y = y
        newRect.width = width
        newRect.height = height

        return newRect

    }

    /**
     * 返回一个比当前rect大或小的rect，他们的中心是一样的。
     */
    fun insetBy(dx: Float, dy: Float):CGRect{
        var newRect = CGRect()
        newRect.x = x + dx
        newRect.y = y + dy
        newRect.width = width - dx * 2
        newRect.height = height - dy * 2

        return newRect
    }







}


