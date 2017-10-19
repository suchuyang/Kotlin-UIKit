package com.thissu.uikit

import android.view.MotionEvent
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.Foundation.UITouch

/**
 * Created by this on 2017/10/5.
 */

/**
 * UIview分发事件
 *
 * 注意在分发事件中，视图要负责识别手势相对于子视图的事件，然后分别进行调用。
 *
 *
 */
fun com.thissu.uikit.UIView.dispatchTouchesEvent(touches:MutableList<UITouch>, withEvent: MotionEvent){

    /**
     *     首先判断点击事件是否属于子视图，如果属于子视图，那么view就不再对此次事件进行处理
     *
     *     不过由于安卓系统事件传递的速度似乎滞后于手指的滑动，尤其是当手指移动速度很快的时候。这时就会出现手指移动过快导致响应视图失去移动事件。
     *     所以在滑动时还得做事件锁定的处理。
     */

    if(touches.size == 0){
        return
    }

    this.touches = touches



    //按下事件，判断
    if(withEvent.actionMasked == MotionEvent.ACTION_DOWN || withEvent.actionMasked == MotionEvent.ACTION_POINTER_DOWN){

        //判断触摸是否在子视图上。
        var isSubviewTouch = false

        //倒序遍历子视图，因为上层的视图是会屏蔽下层视图的事件的。
        for(i in subViews.size - 1 downTo 0 step 1){

            var subview = subViews[i]


            //遍历触摸点，将属于子视图的触摸点放到一起
            val subTouches = mutableListOf<UITouch>()

            for (touch in touches){
                //计算点击位置相对于子视图的坐标
                var subTouch = UITouch()
                subTouch.touchX = touch.touchX - subview.frame.dpiX
                subTouch.touchY = touch.touchY - subview.frame.dpiY
                subTouch.pointerId = touch.pointerId

                //注意计算点击事件的时候，也需要使用dpi尺寸来计算
                if (subTouch.touchX >=0 && subTouch.touchX <= subview.frame.dpiWidth && subTouch.touchY >= 0 && subTouch.touchY <= subview.frame.dpiHeight){
                    //触摸点在子视图的范围内
                    isSubviewTouch = true
                    subTouches.add(subTouch)

                    //如果触摸点在这个视图的范围内，那么可以直接把视图添加到UIView.touchingView中
                }

            }

            NSLog.print("$subview 的 touches:$subTouches")

            if (subTouches.size > 0){

                //如果已经存储了这个视图，那就不用对这个视图进行分发
                if(UIView.touchingView.indexOfValue(subview) == -1){
                    NSLog.print("dispatch touch to $subview")
                    subview.dispatchTouchesEvent(subTouches,withEvent)

                    //对这个点分配了视图之后，后面的子视图就可以不判断了
                    break
                }
            }
        }

        if(!isSubviewTouch){
            touchesBegan(touches, withEvent)

            UIView.touchingView.append(withEvent.actionIndex, this)
            NSLog.print("touching views: ${UIView.touchingView}")
        }

    }
    else if(withEvent.actionMasked == MotionEvent.ACTION_MOVE){

        /**
         * ACTION_MOVE有个bug，即，我们在ACTION_MOVE中无法知道我们移动的是哪一个触摸点。打印日志的话会发现不论怎么样，actionIndex都是0，此处省略一万个***
         *
         * 所以，一定要注意暂时不支持多点触摸事件。
         */

        //如果移动事件的对应触摸点索引与视图匹配，那就直接传递move事件。因为快速移动时，处理函数会滞后于手指移动，所以需要做直接传递的判断。
        var moveView = UIView.touchingView[withEvent.actionIndex]
        if(moveView != null){
            val absoluteFrame = moveView.absoluteFrame()
            val touch = UITouch()

//            NSLog.print("absoluteFrame :$absoluteFrame")
            touch.touchX = withEvent.getX(withEvent.actionIndex) - absoluteFrame.dpiX
            touch.touchY = withEvent.getY(withEvent.actionIndex) - absoluteFrame.dpiY
            touch.pointerId = withEvent.actionIndex

            val tempTouches = mutableListOf<UITouch>()
            tempTouches.add(touch)

            moveView.touchesMoved(tempTouches, withEvent)
        }
    }
    else if(withEvent.actionMasked == MotionEvent.ACTION_UP || withEvent.actionMasked == MotionEvent.ACTION_POINTER_UP){

        //当actionup的时候，根据触摸的ID可以直接找到视图，然后执行视图的touchesEnd事件

        var endView = UIView.touchingView[withEvent.actionIndex]
        if(endView != null){
            val absoluteFrame = endView.absoluteFrame()
            val touch = UITouch()

            touch.touchX = withEvent.getX(withEvent.actionIndex) - absoluteFrame.dpiX
            touch.touchY = withEvent.getY(withEvent.actionIndex) - absoluteFrame.dpiY
            touch.pointerId = withEvent.actionIndex

            val tempTouches = mutableListOf<UITouch>()
            tempTouches.add(touch)

            endView.touchesEnd(tempTouches, withEvent)

            UIView.touchingView.remove(withEvent.actionIndex)
        }
    }
}


/**
 * 判断两个touch的列表是否相同
 *
 */
fun touchesListEqual(list1: MutableList<UITouch>, list2: MutableList<UITouch>) : Boolean{

    if(list1.size != list2.size){
        return false
    }

    //遍历判断相等性
    for(i in 0 until list1.size - 1){

        //只要有一个不相等，就可以断定不同
        if(list1[i] != list2[i]){
            return false
        }
    }

    return true
}
