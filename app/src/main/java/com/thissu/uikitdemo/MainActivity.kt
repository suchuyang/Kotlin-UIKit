package com.thissu.uikitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thissu.uikit.Foundation.NSLog
import com.thissu.uikit.UIAppDelegate
import android.R.attr.orientation
import android.content.res.Configuration


class MainActivity : UIAppDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("UIKit", "main activity on create -----------------------------------------------------")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            //横向
            NSLog.print("横屏")
        } else {
            //竖向
            NSLog.print("竖屏")

        }
    }
}
