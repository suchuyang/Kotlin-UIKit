package com.thissu.uikit

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.SparseArray
import android.view.MotionEvent
import com.thissu.uikit.CoreAnimation.CALayer
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIApplication.UIApplication


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
        }/** 注意frame属性的赋值一定要用copy */

    //

    var backgroundColor: Int = Color.WHITE/**背景颜色*/


    var layer:CALayer = CALayer()/**layer，里面存储绘图和transform相关的东西.layer设置为只读属性*/
        get() = field

    var subViews = arrayListOf<UIView>() /**子视图的数组*/

    var superview: UIView? = null//记录父视图，我们在做某些操作的时候，需要使用这个变量。注意循环引用问题


    /** ---------------------------------------------- 构造函数 -----------------------------------------
     *
     */
    init {
        frame = CGRect(0f,0f,0f,0f)
    }

    constructor(viewFrame: CGRect):this(){

        //注意frame在赋值的时候一定要使用copy。
        frame = viewFrame.copy()
    }

    override fun toString(): String {

        return  "view : {${frame.x},${frame.y},${frame.width},${frame.height}} and color:$backgroundColor and ${super.toString()}"
    }

    /** ---------------------------------------------- 添加和移除视图 -----------------------------------------
     *
     */

    public fun addSubview(view: UIView){

        if(view != null){
            subViews.add(view)

            //添加子视图的同时赋值父视图。
            view.superview = this
        }
        else{
            NSLog.print("low-grade error：添加了一个null的view")
        }
    }

    public fun removeFromSuperview(){



    }


    fun bounds(): CGRect{

        return CGRect(0f,0f, frame.width, frame.height)

    }



    /** ---------------------------------------------- draw -----------------------------------------
     *
     */

    //通知window，更新视图。
    open fun setNeedsDisplay(){
        UIApplication.sharedApplication.keyWindow!!.invalidate()
    }

    /**
     * 绘图函数，子控件和动画统统都是由绘图画出来的。
     *
     * 如果子类要实现自己的绘制，那么就直接覆盖掉这个函数
     * */
    open fun draw(canvas: Canvas){

        //设置绘制的起点，即锚点.
        canvas.translate(frame.dpiX, frame.dpiY)

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
                canvas.drawRoundRect(0f, 0f, frame.dpiWidth, frame.dpiHeight,//矩形区域
                        layer.dpiCornerRadius, layer.dpiCornerRadius, //圆角x方向和y方向的半径
                        paint)//画笔


                //绘制圆角边框
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = layer.dpiBorderWidth

                paint.color = layer.borderColor

                canvas.drawRoundRect(0f, 0f, frame.dpiWidth, frame.dpiHeight,//矩形区域
                        layer.dpiCornerRadius, layer.dpiCornerRadius, //圆角x方向和y方向的半径
                        paint)//画笔
            }
            else{
                canvas.drawRect(0f, 0f, frame.dpiWidth, frame.dpiHeight, paint)
            }
        }
        else {
            // 包含旧的API的代码块
            canvas.drawRect(0f, 0f, frame.dpiWidth, frame.dpiHeight, paint)
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

    /** ---------------------------------------------- open event -----------------------------------------
     *  子类在用到时，重写下面的接口，然后返回对应的状态
     */

    /**
     * 触摸开始的事件，子类如果重写这个接口并且做了相应的操作
     * */
    open fun touchesBegan(touch:UITouch, withEvent: MotionEvent){}

    /**
     * 触摸开始的事件，子类如果重写这个接口并且做了相应的操作
     * */
    open fun touchesMoved(touch:UITouch, withEvent: MotionEvent){}


}