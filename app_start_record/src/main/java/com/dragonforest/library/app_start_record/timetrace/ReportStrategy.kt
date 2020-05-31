package com.dragonforest.library.app_start_record.timetrace

import android.content.Context
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity

/**
 *
 * create by DragonForest at 2020/5/31
 */
interface ReportStrategy {
    fun report(context: Context,traceEntity: TimeTraceEntity)
}