package com.thissu.uikit

import android.app.Activity
import android.os.Bundle

/**
 * Created by this on 2017/10/3.
 *
 * UIAppDelegate，继承Activity，将Activity作为程序的最上层容器，封装加载，前后台，等同于iOS的appdelegate
 */
class UIAppDelegate():Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    /**
     * onResume在activity进入显示状态时调用，不论是第一次创建还是从后台回到前台的显示。
     * 所以在这个里面可以做viewWillAppear的通知
     */

    override fun onResume() {
        super.onResume()
    }


    fun applicationWillEnterForeground() {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }



    /**onPause会在activity消失或锁屏或回到桌面时调用。UIKit将Activity当作唯一的程序容器，所以这个函数
     * 可以和applicationDidEnterBackground关联起来
     *
     */
    override fun onPause() {
        super.onPause()


    }

    fun applicationDidEnterBackground(){

        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }




}