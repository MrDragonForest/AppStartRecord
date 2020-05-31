package com.dragonforest.library.app_start_record.timetrace

import android.content.Context
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity
import com.dragonforest.library.app_start_record.util.Logger
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

/**
 *
 * create by DragonForest at 2020/5/31
 */
class ReportTask(
    var context: Context,
    var chains: LinkedHashMap<String, TraceChain>,
    var reportStrategys: ArrayList<ReportStrategy>
) : Runnable {
    val TAG = this.javaClass.simpleName

    override fun run() {
        var filterChain = chains.filter { it.value.size() > 0 }
        if (filterChain.size == 0) return
        var traceEntity = TimeTraceEntity()
        if (isColdStart()) {
            traceEntity.title = "冷启动"
            traceEntity.startMode = 0
        } else {
            traceEntity.title = "热启动"
            traceEntity.startMode = 1
        }
        traceEntity.traceDate = Date()
        traceEntity.traceContent = Gson().toJson(chains)
        Logger.i(TAG, "report:->\n${traceEntity.traceContent}")
        reportStrategys?.forEach {
            it.report(context, traceEntity)
        }
        clear()
    }

    /**
     * 是否是冷启动
     */
    private fun isColdStart(): Boolean {
        if (chains == null) {
            return false
        }
        return chains.containsKey(ChainField.CHAIN_APPLICATION)
    }

    private fun clear() {
        chains.clear()
    }

}