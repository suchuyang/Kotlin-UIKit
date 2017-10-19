package com.thissu.uikit.Foundation

/**
 * Created by apple on 2017/10/9.
 */
class UITouch {

    var view:com.thissu.uikit.UIView? = null//相对于某个view，当有这个view时，以下的点击位置就是相对于这个view的。

    var touchX: Float = 0f//点击位置的x坐标

    var touchY: Float = 0f//点击位置的y坐标

    var pointerId: Int = -1 //手指点击的ID

    override fun toString(): String {
        return  "touch:Id-$pointerId {$touchX, $touchY}"
    }

    operator override fun equals(other: Any?): Boolean {
        if(other != null){
            if(other is UITouch){
                if(pointerId == other.pointerId){
                    return true
                }
            }
        }

        return false

    }
}