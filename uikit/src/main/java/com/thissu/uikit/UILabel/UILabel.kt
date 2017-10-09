package com.thissu.uikit.UILabel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build

/**
 * Created by apple on 2017/10/9.
 */
class UILabel(): com.thissu.uikit.UIView() {


    var text:String? = null
    var textColor:Int? = null


    /**
     * label的绘图函数 ，绘制文字。
     * */
    override fun draw(canvas: Canvas){

        //设置绘制的起点，即锚点.
        canvas.translate(frame.x, frame.y)

        // 创建画笔
        val paint = Paint()
        // 去锯齿
        paint.isAntiAlias = true


        paint.setColor(backgroundColor)//设置颜色
        paint.setStyle(Paint.Style.FILL)//默认绘图为填充模式

        canvas.drawRect(0f, 0f, frame.width, frame.height, paint)


        paint.setColor(Color.BLACK)//设置颜色

        if(text != null){

            //计算文字的y坐标
            var fontMetrics = paint.fontMetrics
            var y = frame.height / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2



            //在(x,y)中最让人捉急的是y坐标，一般而言，(x，y)所代表的位置是所画图形对应的矩形的左上角点。但在drawText中是非常例外的，y所代表的是基线的位置！
            canvas.drawText(text,0f,y,paint)
        }

    }
}