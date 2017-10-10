package com.thissu.uikit.DemoView

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch


/**
 * Created by this on 2017/10/5.
 */
class DemoView():com.thissu.uikit.UIView() {

    var touchblock:(()->Unit)? = null


    override fun touchesBegan(touch: UITouch, withEvent: MotionEvent) {

        NSLog.print("demo view touch position to view is x:${touch.touchX},y:${touch.touchY}")

        if(touchblock != null){
            touchblock!!()
        }
    }

}