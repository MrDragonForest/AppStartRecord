package com.dragonforest.library.appstartrecord

import android.app.Application
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.appstartrecord.constrants.MyTagField

/**
 *
 * create by DragonForest at 2020/5/31
 */
class MyApplication: Application() {
    override fun onCreate() {
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, TagField.ONCREATE,true)
        super.onCreate()
        initSDK1()
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, MyTagField.INIT_SDK1)

        initSDK2()
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, MyTagField.INIT_SDK2)

        initSDK3()
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, MyTagField.INIT_SDK3)

        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, TagField.ONCREATE_OVER)
    }

    private fun initSDK3() {
        Thread.sleep(10)
    }

    private fun initSDK2() {
        Thread.sleep(15)
    }

    private fun initSDK1() {
        Thread.sleep(20)
    }
}