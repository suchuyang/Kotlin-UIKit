package com.thissu.uikit.Foundation

/**
 * Created by this on 2017/10/3.
 */
class UIScreen {

    var frameRatio:Float = 0f//布局的比例，不论是什么屏幕，我们都使用屏幕宽度除以320，得到这个比例，然后再使用这个比例得到相对的高度。

    //屏幕的绝对宽和高
    var absoluteWidth: Float = 0f
    var absoluteHeight: Float = 0f

    //屏幕的相对宽和高，宽固定为320f，高度则计算得到。
    var relativeWidth: Float = 320f
    var relativeHeight: Float = 0f

    companion object {
        val shared = UIScreen()
    }

    /**
     * 加载屏幕的相关属性，注意这个接口只能在主的window加载时调用。
     */
    fun launchScreenProperty(){

        //计算屏幕比例
        frameRatio = absoluteWidth / relativeWidth

        //320宽度下对应的高度。
        relativeHeight = absoluteHeight / frameRatio

    }
}