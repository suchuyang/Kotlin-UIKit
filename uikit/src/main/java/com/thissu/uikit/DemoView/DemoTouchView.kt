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


    override fun touchesBegan(touches: MutableList<UITouch>, withEvent: MotionEvent) {


        val atouch = touches.first()
        NSLog.print("touchesBegan $name :${atouch.touchX},y:${atouch.touchY}")


        //注意判断移动距离时还得使用Android原有的判断方式，因为UITouch判断存在问题，会出现视图抖动的现象。
        lastX = withEvent.getX(withEvent.actionIndex)
        lastY = withEvent.getY(withEvent.actionIndex)
    }

    override fun touchesMoved(touches: MutableList<UITouch>, withEvent: MotionEvent) : Boolean {


        //开始移动
        var currentx = withEvent.getX(withEvent.actionIndex)
        var currenty = withEvent.getY(withEvent.actionIndex)

        NSLog.print("touchesMoved $name :{$currentx,$currenty}  last:{$lastX,$lastY}")
        //注意获取到的是dpi的位置，需要转换成320比例的
        frame.x += (currentx - lastX)/frame.frameRatio
        frame.y += (currenty - lastY)/frame.frameRatio

        lastX = currentx
        lastY = currenty

        //改变了尺寸之后要调用setNeedsDisplay，否则不会触发重新绘制视图的动作。
        setNeedsDisplay()

        return true

    }

    override fun touchesEnd(touches: MutableList<UITouch>, withEvent: MotionEvent): Boolean {
        val atouch = touches.first()

        NSLog.print("touchesEnd $name :${atouch.touchX},y:${atouch.touchY}")
        return true

    }
}