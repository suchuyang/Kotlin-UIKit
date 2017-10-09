package com.thissu.uikit.DemoView

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog


/**
 * Created by this on 2017/10/5.
 */
class DemoView():com.thissu.uikit.UIView() {

     override fun touchesBegan(withEvent: MotionEvent){

        NSLog.print("DemoView touches began")
    }


}