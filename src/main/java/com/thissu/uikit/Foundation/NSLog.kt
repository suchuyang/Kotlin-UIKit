package com.thissu.uikit.Foundation

import android.util.Log

/**
 * Created by apple on 2017/9/27.
 */


class NSLog {

    val logTag: String = "UIKit"

    companion object {

        fun print(logStr: String){

            Log.d("UIKit",logStr)
        }


    }
}