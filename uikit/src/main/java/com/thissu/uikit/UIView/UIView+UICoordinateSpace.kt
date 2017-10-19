package com.thissu.uikit
import android.graphics.Point
import com.thissu.uikit.GoreGraphics.CGPoint
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIView

/**
 * Created by this on 2017/10/3.
 */

/**
 * 把坐标点转换到toView上，获取其相对坐标。
 */
fun com.thissu.uikit.UIView.convert(point: CGPoint, toView: UIView){


}

/**
 * 获取view在屏幕上的绝对坐标，
 *
 * 获取的方法是递归父视图，递归的时候增加坐标，直到isRootWindowView为true
 */
fun com.thissu.uikit.UIView.absoluteFrame(): CGRect{

    var absoluteFrame = frame.copy()

    //把absoluteFrame转换到beConvertView的父视图上
    fun convertFrameToSuperView(beConvertView: UIView){

        if(beConvertView.superview != null){
            absoluteFrame.x += beConvertView.superview!!.frame.x
            absoluteFrame.y += beConvertView.superview!!.frame.y

            if(beConvertView.superview!!.isRootWindowView){
                return
            }
            else{
                convertFrameToSuperView(beConvertView.superview!!)
            }
        }

    }

    convertFrameToSuperView(this)


    return absoluteFrame



}