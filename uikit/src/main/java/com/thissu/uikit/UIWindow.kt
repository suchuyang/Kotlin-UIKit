package com.thissu.uikit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.thissu.uikit.GoreGraphics.CGRect

import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.UIViewController.UIViewController

import android.view.WindowManager
import com.thissu.uikit.Foundation.UIScreen
import com.thissu.uikit.Foundation.UITouch
import com.thissu.uikit.UIApplication.UIApplication


/**
 * Created by apple on 2017/9/29.
 *
 * 这个类是UIKit和Android衔接的入口，也是安卓各服务在UIKit中的入口，它是app视图的底层容器，所有的工作都将在他上面执行。
 *
 * window在activity上充满全屏，然后window之下自己定义toolbar，navigationbar，tabbar等视图规范。规范参考iOS
 *
 * 注意在没有理解UIWindow和UIViewController之前，最好不要重写UIWindow
 */
open class UIWindow : View{

    final var rootViewController:UIViewController? = null//!<根视图。这个变量唯一，window只展示rootViewController上的内容
        get() = field
        set(value) {

            //卸载旧的视图
            if(field != null){
                field!!.viewDidUnload()
            }

            //赋值
            field = value

            //展示新的视图
            if(field != null){
                this.invalidate()
            }
        }

    /**  ----------------------------------------------- 构造函数  -----------------------------------------------
     *
     * */
    constructor(context: Context):super(context){
        NSLog.print("UIWindow one parameter constructor")

        windowWillFinishLaunching()

    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs){

        NSLog.print(" UIWindow two parameter constructor")
        windowWillFinishLaunching()



    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs,  defStyleAttr){

        NSLog.print("UIWindow three parameter constructor")
        windowWillFinishLaunching()


    }

    /** 这个构造函数是参数最全的构造函数，
     *
     * @param context 视图运行在这个上下文中，通过这个上下文，视图可以拿到资源文件，主题等。
     *
     * @param attrs XML中定义的属性
     *
     * @param defStyleAttr defStyleAttr是定义在theme中的一个引用，这个引用指向一个style资源，而这个style资源包含了一些TypedArray的默认值。
     *
     * @param defStyleRes 一个支持默认样式的资源id，这个参数只会在defStyleAttr是0或者无效时呗使用
     * */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int):super(context, attrs, defStyleAttr, defStyleRes){

        NSLog.print("UIWindow four parameter constructor")
        windowWillFinishLaunching()

    }


    /** 程序加载完成后执行的一些动作
     * */
    final  fun windowWillFinishLaunching(){


        //赋值屏幕的宽高等属性
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // 方法1,获取屏幕的默认分辨率
        val display = manager.defaultDisplay // getWindowManager().getDefaultDisplay();

        var realSize:Point = Point()
        display.getRealSize(realSize)

        var rectSize:Rect = Rect()
        display.getRectSize(rectSize)

        var size:Point = Point()
        display.getSize(size)

        UIScreen.shared.screenWidth = size.x.toFloat()
        UIScreen.shared.screenHeight = size.y.toFloat()

        //记录全局唯一的window
        UIApplication.sharedApplication.keyWindow = this

    }


    /** 当我们的UIWindow被添加到Android的真实window上时会调用这个接口。
     *
     * */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.setBackgroundColor(Color.WHITE)

        windowWillLoad()

        if( rootViewController == null){
            //初始化视图控制器
            rootViewController = UIViewController()
        }


        NSLog.print("window onAttachedToWindow")
    }

    /**
     * window加载时调用的接口，子类可以重写。
     * 如果子类在这个接口中使用了自己的rootVC，那么我们就不需要再自己赋值默认的vc了
     *
     */
    open fun windowWillLoad(){ }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        NSLog.print("UIView onDraw")
        if(canvas != null){

            if (!rootViewController!!.isCurrentViewController){
                //调用viewWillAppear，开始进行绘图的工作
                rootViewController!!.viewWillAppear()

                rootViewController?.drawViewsOn(canvas = canvas)

                rootViewController!!.viewDidAppear()

            }
            else{

                rootViewController?.drawViewsOn(canvas = canvas)

                //调用vc的绘制完成接口

            }





            //绘图结束之后需要调用一个视图绘制完成的函数。
        }
        else{
//            NSLog.print("high-grade error: 画布不存在！")
        }

    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        NSLog.print("dispatchTouchEvent")

        val touch = UITouch()

        touch.touchX = event!!.getX()
        touch.touchY = event!!.getY()


        if (event?.action == MotionEvent.ACTION_DOWN){
            rootViewController!!.view!!.dispatchTouchesBeganEvent(touch,event!!)
        }

        return super.dispatchTouchEvent(event)
    }
}