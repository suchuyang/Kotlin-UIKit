package com.thissu.uikit.UIApplication

import com.thissu.uikit.UIWindow

/**
 * Created by this on 2017/10/3.
 *
 * 程序管理器，存放程序级的全局变量，暂时不可以继承
 */
class UIApplication {

    //程序的主窗口
    var keyWindow:UIWindow? = null

    companion object {
        val sharedApplication = UIApplication()
    }
}