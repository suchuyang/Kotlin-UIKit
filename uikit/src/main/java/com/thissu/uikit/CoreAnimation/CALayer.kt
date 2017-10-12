package com.thissu.uikit.CoreAnimation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.thissu.uikit.Foundation.ERROR_HIGH_GRADE
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UIScreen

/**
 * Created by apple on 2017/9/29.
 *
 * layer对象，封装绘图相关的一些变量
 */
class CALayer() {

    var cornerRadius: Float = 0f//圆角
        set(value) {
            field = value
            dpiCornerRadius = value * frameRatio
        }

    var borderWidth: Float = 0f // 边框宽度
        set(value) {
            field = value
            dpiBorderWidth = value * frameRatio
        }

    var borderColor: Int = Color.WHITE//!<边框颜色

    //以后所有涉及到绘图的尺寸相关属性，都需要经过这么一个转换，还好这样的属性还不是很多。
    var frameRatio: Float = 0f//frame的比例，这个比例是真实屏幕分辨率和320的比例

    var dpiCornerRadius: Float = 0f

    var dpiBorderWidth: Float = 0f

    init {

        //初始化里面要把比例赋值，注意如果frame的初始化在UIScreen之前执行了，那么比例就会出错。所以一定要做赋值校验。
        frameRatio = UIScreen.shared.frameRatio

        if (frameRatio == 0f){
            NSLog.print("${ERROR_HIGH_GRADE} 屏幕比例为0，layer 的创建超前了")
            frameRatio = 1f
        }
    }


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
