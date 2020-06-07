package com.dragonforest.library.appstartrecord

import android.app.Application
import android.content.Context
import android.content.Intent
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.ReportStrategy
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.app_start_record.util.Logger
import com.dragonforest.library.appstartrecord.constrants.MyTagField
import com.dragonforest.library.appstartrecord.service.InitService

/**
 *
 * create by DragonForest at 2020/5/31
 */
class MyApplication: Application() {
    override fun onCreate() {
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, TagField.ONCREATE,true)
        super.onCreate()
//        MockSDK.init(this)
        startService(Intent(applicationContext,InitService::class.java))
        TraceTimeMonitor.trace(ChainField.CHAIN_APPLICATION, TagField.ONCREATE_OVER)

        TraceTimeMonitor.addReportStategy(object:ReportStrategy{
            override fun report(context: Context, traceEntity: TimeTraceEntity) {
                //上传数据到服务器
                Logger.i("report","上传到服务器")
            }
        })
    }
}