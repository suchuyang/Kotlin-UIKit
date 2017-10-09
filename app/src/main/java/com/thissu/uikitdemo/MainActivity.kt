package com.thissu.uikitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thissu.uikit.Foundation.NSLog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("UIKit", "main activity on create -----------------------------------------------------")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
