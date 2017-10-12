package com.thissu.uikit.Foundation

import android.util.Log

/**
 * Created by apple on 2017/9/27.
 */

val ERROR_HIGH_GRADE = "high-grade-error"//最高重要度的错误
val ERROR_MIDDLE_GRADE = "middle-grade-error"//中等重要的错误
val ERROR_LOW_GRADE = "low-grade-error"//重要度最低的错误

class NSLog {

    val logTag: String = "UIKit"

    companion object {

        fun print(logStr: String){

            Log.d("UIKit",logStr)
        }


    }
}