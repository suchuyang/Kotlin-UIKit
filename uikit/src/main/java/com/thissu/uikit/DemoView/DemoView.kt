package com.thissu.uikit.DemoView

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch


/**
 * Created by this on 2017/10/5.
 */
class DemoView():com.thissu.uikit.UIView() {

    var touchblock:(()->Unit)? = null

    var lastX = 0f
    var lastY = 0f


    override fun touchesBegan(touches: MutableList<UITouch>, withEvent: MotionEvent) {

//        NSLog.print("demo view touch position to view is x:${touch.touchX},y:${touch.touchY}")

//        if(touchblock != null){
//            touchblock!!()
//        }
        lastX = withEvent.x
        lastY = withEvent.y
    }

    override fun touchesMoved(touches: MutableList<UITouch>, withEvent: MotionEvent) : Boolean {

        //开始移动
        var currentx = withEvent.x
        var currenty = withEvent.y

//         NSLog.print("current:{$currentx,$currenty}  last:{$lastX,$lastY}")
        //注意获取到的是dpi的位置，需要转换成320比例的
        frame.x += (currentx - lastX)/frame.frameRatio
        frame.y += (currenty - lastY)/frame.frameRatio

        lastX = currentx
        lastY = currenty

        setNeedsDisplay()

        return true

    }

}