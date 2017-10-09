package com.thissu.uikit.UIViewController

import android.graphics.Canvas
import android.graphics.Color
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UIScreen
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIApplication.UIApplication
import com.thissu.uikit.UIView

/**
 * Created by apple on 2017/9/30.
 *
 * UIViewController，用于控制视图的控制层，其加载逻辑模仿iOS的加载逻辑，然后与Android的View结合起来。
 *
 */
open class UIViewController() {

    var isCurrentViewController = false//标记vc是否是当前正在显示的vc。许多生命周期的判断都需要用这个标志

    var view: com.thissu.uikit.UIView? = UIView()
        get() = field
        set(value) {
            field = value

            //如果视图空了，就调用unload方法
            if(value == null){
                viewDidUnload()
            }
            else{
                NSLog.print("viewController set a new view")
                //更新视图
                UIApplication.sharedApplication.keyWindow?.invalidate()
            }

        }

    /**
     * 创建ViewController时调用
     * */
    init {

        //初始化结束后加载视图
        loadView()


        //视图加载完成后，视图控制器才算是真正加载
        viewDidLoad()
    }

    /** ---------------------------------------------- 生命周期 -----------------------------------------
     *
     * 目前生命周期的大部分函数还都没有发挥其应有的作用，这是因为对生命周期和视图逻辑理解不到位导致的，以后有了什么新的发现的话，再来完善吧。
     */

    /**
     * view在这个函数里完成初始化和加载的工作,比方说初始属性和数据
     * */
    public fun loadView(){
        var frame = CGRect(0f,0f,UIScreen.shared.screenWidth,UIScreen.shared.screenHeight)
        view?.frame = frame
        view?.backgroundColor = Color.WHITE

    }

    /**
     * 在view加载完成之后，调用viewDidLoad，表示视图加载完了
     * */
    open fun viewDidLoad(){


    }

    /**
     * ViewController将要出现，子类重写这个函数可以做一些相对应的工作，但无法在这里绘图。子类不需要调用super.viewWillAppear
     *
     * */
    open fun viewWillAppear(){

        isCurrentViewController = true

    }

    /**
     * ViewController展示完成
     * */
    open fun viewDidAppear(){

    }

    /**
     * ViewController将要消失
     * */
    open fun viewWillDisappear(){
        isCurrentViewController = false
    }

    /**
     * ViewController消失完成
     * */
    open fun viewDidDisappear(){

    }

    /**
     * ViewController的视图被赋值为null时调用
     * */
    open fun viewDidUnload(){

    }

    /** ---------------------------------------------- draw -----------------------------------------
     *
     */

    final fun drawViewsOn(canvas: Canvas){
        view?.draw(canvas = canvas)
    }


}