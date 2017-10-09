package com.thissu.uikit

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.view.MotionEvent
import com.thissu.uikit.CoreAnimation.CALayer
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.GoreGraphics.CGRect


/**
 * Created by apple on 2017/9/29.
 *
 * UIView基于基本的draw功能重新定义，自己管理内部的子视图和各自属性及动作传递。
 */
open class UIView(){

    /** ---------------------------------------------- property -----------------------------------------
     *
     */

    //视图的尺寸属性
    final var  frame: CGRect = CGRect()
        get() = field
        set(value) {
            field = value.copy()
            NSLog.print("view set new frame:$value")
            NSLog.print("view set frame result:$frame")
        }/** 注意frame属性的赋值一定要用copy */
    //

    var backgroundColor: Int = Color.WHITE/**背景颜色*/


    var layer:CALayer = CALayer()/**layer，里面存储绘图和transform相关的东西.layer设置为只读属性*/
        get() = field

    var subViews = mutableListOf<UIView>()/**子视图的数组*/


    /** ---------------------------------------------- 构造函数 -----------------------------------------
     *
     */
    init {
        NSLog.print("UIView init")
        frame = CGRect(0f,0f,0f,0f)
    }


    constructor(viewFrame: CGRect):this(){

        //注意frame在赋值的时候一定要使用copy。
        frame = viewFrame.copy()
    }

    public fun addSubview(view: UIView){

        if(view != null){
            subViews.add(view)
        }
        else{
            NSLog.print("low-grade error：添加了一个null的view")
        }
    }


    override fun toString(): String {

        return  "view :{${frame.x},${frame.y},${frame.width},${frame.height}} and color:$backgroundColor"
    }


    /** ---------------------------------------------- draw -----------------------------------------
     *
     */

    //通知window，更新视图。
    open fun setNeedsDisplay(){

    }

    /**
     * 绘图函数，子控件和动画统统都是由绘图画出来的。
     *
     * 如果子类要实现自己的绘制，那么就直接覆盖掉这个函数
     * */
    open fun draw(canvas: Canvas){

        //设置绘制的起点，即锚点.
        canvas.translate(frame.x, frame.y)
        NSLog.print("view`s frame:${frame}")

        // 创建画笔
        val paint = Paint()
        // 去锯齿
        paint.isAntiAlias = true


        paint.setColor(backgroundColor)//设置颜色
        paint.setStyle(Paint.Style.FILL)//默认绘图为填充模式

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 包含新API的代码块
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
        }
        else {
            // 包含旧的API的代码块
            canvas.drawRect(0f, 0f, frame.width, frame.height, paint)
        }

        //绘制子视图
        for( subview in subViews ){
            canvas.save()//保存画布状态，在绘制完子视图后要恢复。绘制子视图时设置新的锚点，但是新的锚点位置居然时相对于其父视图的，不知道什么原理，但真他娘的舒坦。

            subview.draw(canvas = canvas)

            canvas.restore()
        }

        /**
         * public void drawCircle (float cx, float cy, float radius, Paint paint)
        cx：圆心的x坐标。
        cy：圆心的y坐标。
        radius：圆的半径。
        paint：绘制时所使用的画笔。
         * */
        //canvas.drawCircle(_frame.width / 2, _frame.height / 2, _frame.width / 2, paint)

    }

    open fun touchesBegan(withEvent: MotionEvent){

        NSLog.print("UIview touches began")

    }

}