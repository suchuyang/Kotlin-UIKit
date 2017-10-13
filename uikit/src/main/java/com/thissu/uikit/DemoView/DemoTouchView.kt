package com.thissu.uikit.DemoView

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch
import com.thissu.uikit.UIView

/**
 * Created by apple on 2017/10/13.
 */
class DemoTouchView(): UIView() {

    var lastX = 0f
    var lastY = 0f


    override fun touchesBegan(touch: UITouch, withEvent: MotionEvent) {

        NSLog.print("demo view touch position to view is x:${touch.touchX},y:${touch.touchY}")

//        if(touchblock != null){
//            touchblock!!()
//        }
        lastX = withEvent.x
        lastY = withEvent.y
    }

    override fun touchesMoved(touch: UITouch, withEvent: MotionEvent) {

        //开始移动
        var currentx = withEvent.x
        var currenty = withEvent.y

        NSLog.print("current:{$currentx,$currenty}  last:{$lastX,$lastY}")
        //注意获取到的是dpi的位置，需要转换成320比例的
        frame.x += (currentx - lastX)/frame.frameRatio
        frame.y += (currenty - lastY)/frame.frameRatio

        lastX = currentx
        lastY = currenty

        setNeedsDisplay()

    }
}