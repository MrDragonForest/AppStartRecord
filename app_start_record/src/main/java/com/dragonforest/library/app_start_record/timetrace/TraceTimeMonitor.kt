package com.dragonforest.library.app_start_record.timetrace

import android.content.Context
import android.text.TextUtils
import com.dragonforest.library.app_start_record.database.TimeTraceDatabase
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity
import com.dragonforest.library.app_start_record.util.Logger
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/***
 *  create by DragonForest on 2020/5/28
 */
object TraceTimeMonitor {
    val TAG = "TraceTimeMonitor"

    //    var chains = LinkedHashMap<String, LinkedList<TraceTag>>()
    var chains = LinkedHashMap<String, TraceChain>()

    var reportStrategys = ArrayList<ReportStrategy>()

    var excutor: ThreadPoolExecutor =
        ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, LinkedBlockingDeque<Runnable>(1))

    init {
        reportStrategys.add(object : ReportStrategy {
            override fun report(context: Context, traceEntity: TimeTraceEntity) {
                TimeTraceDatabase.getDatabase(context).timeTraceDao().insert(traceEntity)
            }
        })
    }

    fun addReportStategy(reportStrategy: ReportStrategy) {
        reportStrategy?.let {
            reportStrategys.add(reportStrategy)
        }
    }

    /**
     * 记录某个tag的时间
     * 注：
     *      1.TraceTag.TAG_APPLICATION_ATTACH_CONTEXT 类型的tag会重置所有
     *      2.重复的tag只记录第一次的时间
     *
     */
    fun trace(chainName: String, tagName: String, isStartTagInChain: Boolean = false) {
        if (chainName == ChainField.CHAIN_APPLICATION && tagName == TagField.ATTACH_BASE_CONTEXT) {
            clear()
        }
        if (isStartTagInChain) {
            clearChain(chainName)
            var chain = TraceChain()
            chain.chainName = chainName
            chain?.beginTagName = tagName
            chains.put(chainName, chain)
        }
        val chain = chains.get(chainName)
        if (!TextUtils.isEmpty(chain?.beginTagName)) {
            var curr = System.currentTimeMillis()
            chain?.add(TraceTag(tagName, curr))
            chain?.let {
                var size = it.size()
                if (size > 1) {
                    var timeDivide = curr - chain.tagList?.get(size - 2)?.tagTimeStamp!!
                    Logger.i(TAG, "耗时：${timeDivide}ms")
                }
            }

            Logger.i(TAG, "trace-> ${chainName}/${tagName}")
        }
    }

    fun report(context: Context) {
        excutor.execute(ReportTask(context, chains, reportStrategys))
    }

    private fun clear() {
        chains.clear()
    }

    private fun clearChain(chainName: String) {
        chains[chainName]?.clear()
        chains.remove(chainName)
    }
}
