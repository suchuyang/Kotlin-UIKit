package com.thissu.uikit

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.thissu.uikit.CoreAnimation.CALayer
import com.thissu.uikit.GoreGraphics.CGRect


/**
 * Created by apple on 2017/9/29.
 *
 * UIView基于基本的draw功能重新定义，自己管理内部的子视图和各自属性及动作传递。
 */
class UIView(){

    /** ---------------------------------------------- property -----------------------------------------
     *
     */

    //视图的尺寸属性
    final var  frame: CGRect = CGRect()
        get() = field
        set(value) {field = value.copy()}/** 注意frame属性的赋值一定要用copy */
    //

    var backgroundColor: Int = Color.WHITE/**背景颜色*/


    var layer:CALayer = CALayer()/**layer，里面存储绘图和transform相关的东西.layer设置为只读属性*/
        get() = field


    /** ---------------------------------------------- 构造函数 -----------------------------------------
     *
     */


    constructor(viewFrame: CGRect):this(){

        //注意赋值的时候一定要使用copy。
        frame = viewFrame.copy()

    }


    /** ---------------------------------------------- draw -----------------------------------------
     *
     */

    /**
     * 绘图函数，子控件和动画统统都是由绘图画出来的。
     *
     * 如果子类要实现自己的绘制，那么就直接覆盖掉这个函数
     * */
    fun draw(canvas: Canvas){

        //设置绘制的起点，即锚点.
        canvas.translate(frame.x, frame.y)

        // 创建画笔
        val paint = Paint()
        // 去锯齿
        paint.isAntiAlias = true


        paint.setColor(backgroundColor)//设置颜色
        paint.setStyle(Paint.Style.FILL)//默认绘图为填充模式



        if(layer.cornerRadius > 0f){

            /** drawRoundRect是API-21（Android 5.0）才有的接口，如果需要在低版本上运行，那就得来改写这里的代码了。
             *
             * */

            canvas.drawRoundRect(0f, 0f, frame.width, frame.height,//矩形区域
                    layer.cornerRadius, layer.cornerRadius, //圆角x方向和y方向的半径
                    paint)//画笔


            //绘制圆角边框
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = layer.borderWidth

            paint.color = layer.borderColor

            canvas.drawRoundRect(0f, 0f, frame.width, frame.height,//矩形区域
                    layer.cornerRadius, layer.cornerRadius, //圆角x方向和y方向的半径
                    paint)//画笔
        }
        else{
            canvas.drawRect(0f, 0f, frame.width, frame.height, paint)
        }



        /**
         * public void drawCircle (float cx, float cy, float radius, Paint paint)
        cx：圆心的x坐标。
        cy：圆心的y坐标。
        radius：圆的半径。
        paint：绘制时所使用的画笔。
         * */
        //            canvas.drawCircle(_frame.width / 2, _frame.height / 2, _frame.width / 2, paint)

    }

}