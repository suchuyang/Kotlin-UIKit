package com.thissu.uikit.DemoView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.UIWindow

/**
 * Created by this on 2017/10/3.
 *
 * 演示视图，继承自UIWindow，用于自定义window。参见UIWindow类说明
 *
 * 之所以把window的自定义放在UIKit库中，是因为在示例项目中无法自定义类，所以只能采用这种方式。
 */
class DemoWindow:UIWindow {

    //自定义window时必须实现四个构造函数

    /**  ----------------------------------------------- 构造函数  -----------------------------------------------
     *
     * */
    constructor(context: Context):super(context){
        NSLog.print("DemoWindow one parameter constructor")

    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs){

        NSLog.print(" DemoWindow two parameter constructor")


    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs,  defStyleAttr){

        NSLog.print("DemoWindow three parameter constructor")

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

        NSLog.print("DemoWindow four parameter constructor")
    }

    override fun windowWillLoad() {
        var demoVC = DemoTouchViewController()

        this.rootViewController = demoVC
    }


}