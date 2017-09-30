package com.thissu.uikit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.thissu.uikit.GoreGraphics.CGRect

import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.UIViewController.UIViewController


/**
 * Created by apple on 2017/9/29.
 *
 * 这个类是UIKit和Android衔接的入口，也是安卓各服务在UIKit中的入口，它是app视图的底层容器，所有的工作都将在他上面执行。
 *
 * window在activity上充满全屏，然后window之下自己定义toolbar，navigationbar，tabbar等视图规范。规范参考iOS
 *
 * 注意在没有理解UIWindow和UIViewController之前，最好不要重写UIWindow
 */
class UIWindow : View{

    final var rootViewController:UIViewController? = null//!<根视图。这个变量属性唯一，window只展示rootViewController上的内容

    /**  ----------------------------------------------- 构造函数  -----------------------------------------------
     *
     * */
    constructor(context: Context):super(context){
        NSLog.print(" one parameter constructor")

        initRootViewController()

    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs, 0, 0){

        NSLog.print(" two parameter constructor")
        initRootViewController()


    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs,  defStyleAttr, 0){

        NSLog.print(" three parameter constructor")
        initRootViewController()

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

        NSLog.print(" four parameter constructor")
        initRootViewController()

    }

    /** 初始化我们的视图控制器。
     * 如果要定制自己的UIViewController，那就要重写这个函数，然后赋值自己的视图控制器
     * */
    final fun initRootViewController(){

        this.setBackgroundColor(Color.WHITE)


        //初始化视图控制器
        rootViewController = UIViewController()

        //初始化结束后加载视图
        rootViewController?.loadView()

        //视图加载完成后，视图控制器才算是真正加载
        rootViewController?.viewDidLoad()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(canvas != null){
            //调用viewWillAppear，开始进行绘图的工作
            rootViewController?.viewWillAppear(canvas = canvas)

            //绘图结束之后调用viewDidAppear表示视图已经展示好了。
            rootViewController?.viewDidAppear()
        }
        else{
            NSLog.print("high-grade error: 画布不存在！")
        }

    }
}