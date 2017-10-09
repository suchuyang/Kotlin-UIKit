package com.thissu.uikit.UIViewController

import android.graphics.Canvas
import android.graphics.Color
import com.thissu.uikit.GoreGraphics.CGRect
import com.thissu.uikit.UIView

/**
 * Created by apple on 2017/9/30.
 *
 * UIViewController，用于控制视图的控制层，其加载逻辑模仿iOS的加载逻辑，然后与Android的View结合起来。
 *
 */
class UIViewController() {

    var view: com.thissu.uikit.UIView? = UIView()
        get() = field
        set(value) {
            field = value

            //如果视图空了，就调用unload方法
            if(value == null){
                viewDidUnload()
            }

        }

    /**
     * 创建ViewController时调用
     * */
    init {

    }

    /** ---------------------------------------------- 生命周期 -----------------------------------------
     *
     * 目前生命周期的大部分函数还都没有发挥其应有的作用，这是因为对生命周期和视图逻辑理解不到位导致的，以后有了什么新的发现的话，再来完善吧。
     */

    /**
     * view在这个函数里完成初始化和加载的工作,比方说初始属性和数据
     * */
    fun loadView(){
        var frame = CGRect(200f,200f,300f,300f)
        view?.frame = frame
        view?.backgroundColor = Color.YELLOW

    }

    /**
     * 在view加载完成之后，调用viewDidLoad，表示视图加载完了
     * */
    fun viewDidLoad(){


    }

    /**
     * ViewController将要出现
     *
     * 使用final，是因为要先绘制子视图，然后调用子类的viewWillAppear，做一些视图将要展示时的工作。
     * */
    final fun viewWillAppear(canvas: Canvas){
        viewWillAppear()

        view?.draw(canvas = canvas)

    }

    /**
     * ViewController将要出现，子类重写这个函数可以做一些相对应的工作，但无法在这里绘图。子类不需要调用super.viewWillAppear
     *
     * */
    fun viewWillAppear(){

    }

    /**
     * ViewController展示完成
     * */
    fun viewDidAppear(){

    }

    /**
     * ViewController将要消失
     * */
    fun viewWillDisappear(){

    }

    /**
     * ViewController消失完成
     * */
    fun viewDidDisappear(){

    }

    /**
     * ViewController的视图被赋值为null时调用
     * */
    fun viewDidUnload(){

    }

    /** ---------------------------------------------- 生命周期结束 -----------------------------------------
     *
     */

}