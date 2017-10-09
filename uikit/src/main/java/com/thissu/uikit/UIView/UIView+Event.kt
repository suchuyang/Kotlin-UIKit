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
fun com.thissu.uikit.UIView.dispatchTouchesBeganEvent(touch:UITouch, withEvent: MotionEvent){

    //首先判断点击事件是否属于子视图，如果属于子视图，那么view就不再对此次事件进行处理，否则就调用touchesBegan接口

    var isSubviewTouch = false

    for (subview in subViews){

        //计算点击位置相对于子视图的坐标
        var subTouch = UITouch()
        subTouch.touchX = touch.touchX - subview.frame.x
        subTouch.touchY = touch.touchY - subview.frame.y

        if (subTouch.touchX >=0 && subTouch.touchX <= subview.frame.width && subTouch.touchY >= 0 && subTouch.touchY <= subview.frame.height){
            //当点在子视图的范围内时，才进行子视图的分发
            subview.dispatchTouchesBeganEvent(subTouch,withEvent)
            isSubviewTouch = true
        }

    }

    if (!isSubviewTouch){
        //如果点击位置不属于子视图，那么就调用当前视图的事件函数
        touchesBegan(touch,withEvent)
    }

}


