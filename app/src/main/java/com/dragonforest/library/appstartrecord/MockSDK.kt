package com.dragonforest.library.appstartrecord

import android.content.Context
import com.dragonforest.library.app_start_record.util.Logger

/**
 * 模拟SDK
 * create by DragonForest at 2020/6/8
 */
object MockSDK {
    val TAG = this.javaClass.simpleName
    fun init(context:Context){
        Logger.i(TAG,"MockSDK初始化开始")
        Thread.sleep(5000)
        Logger.i(TAG,"MockSDK初始化结束")
    }
}