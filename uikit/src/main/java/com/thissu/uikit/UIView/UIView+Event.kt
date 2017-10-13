package com.thissu.uikit

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch

/**
 * Created by this on 2017/10/5.
 */

/**
 * UIview分发事件
 */
fun com.thissu.uikit.UIView.dispatchTouchesEvent(touch:UITouch, withEvent: MotionEvent){

    /**
     *     首先判断点击事件是否属于子视图，如果属于子视图，那么view就不再对此次事件进行处理
     *
     *     不过由于安卓系统事件传递的速度似乎滞后于手指的滑动，尤其是当手指移动速度很快的时候。这时就会出现手指移动过快导致响应视图失去移动事件。
     */

    var isSubviewTouch = false

    //倒序遍历子视图，因为上层的视图是会屏蔽下层视图的事件的。
    for(i in subViews.size - 1 downTo 0 step 1){

        var subview = subViews[i]

        //计算点击位置相对于子视图的坐标
        var subTouch = UITouch()
        subTouch.touchX = touch.touchX - subview.frame.dpiX
        subTouch.touchY = touch.touchY - subview.frame.dpiY

        //注意计算点击事件的时候，也需要使用dpi尺寸来计算
        if (subTouch.touchX >=0 && subTouch.touchX <= subview.frame.dpiWidth && subTouch.touchY >= 0 && subTouch.touchY <= subview.frame.dpiHeight){
            //当点在子视图的范围内时，才进行子视图的分发
            subview.dispatchTouchesEvent(subTouch,withEvent)
            isSubviewTouch = true
            break
        }
    }


    if (!isSubviewTouch){
        //如果点击位置不属于子视图，说明已经找到了相应视图，那么就调用当前视图的事件函数

        when (withEvent?.action){

            MotionEvent.ACTION_DOWN -> {
                touchesBegan(touch, withEvent)
            }
            MotionEvent.ACTION_MOVE -> {
                touchesMoved(touch, withEvent)
            }


        }

    }

}



