package com.thissu.uikit.CoreAnimation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * Created by apple on 2017/9/29.
 *
 * layer对象，封装绘图相关的一些变量
 */
class CALayer() {

    var cornerRadius: Float = 0f//圆角

    var borderWidth: Float = 0f // 边框宽度

    var borderColor: Int = Color.WHITE//!<边框颜色


    /** 根据layer的变量对paint和canvas进行设置。
     *
     * kotlin 中参数以指针的形式进行传递，所以这里的修改可以在外面生效。
     *
     * 参数可以为null，表示这个参数不需要layer来设置.赋值一个默认的null，那么不修改的参数就可以不传了。
     *
     * */
    fun setPaintAndCanvas(paint: Paint? = null, canvas: Canvas? = null){

        if (paint != null){
            paint.color = borderColor
        }
    }

    fun calculateDrawRect(){}


    fun drawType():DrawType{

        return DrawType.Rect
    }


}

enum class DrawType(val type:Int = 0x0001){

    Rect(0x0001),//矩形

    RoundRect(0x0002),//圆角矩形

    Circle(0x0003),//圆


}
