package com.thissu.uikit.GoreGraphics

/**
 * Created by apple on 2017/9/29.
 *
 * 因为kotlin 没有struct类型，所以只能用类来表示CGRect。而类在赋值的时候是指针赋值，所以大家一定要注意，在自定义的视图里，frame一定要使用copy赋值
 */



data class CGRect(var x: Float = 0f, var y: Float = 0f, var width: Float = 0f, var height: Float = 0f){

    //

    override fun toString(): String {
        return "(x=${x}, y=${y}, width=${width}, height=${height})"
    }

    //操作符重载，判断是否相等
//    operator override fun  equals(other: Any?): Boolean{
//        return  true
//    }




}


