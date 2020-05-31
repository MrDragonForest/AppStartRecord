package com.dragonforest.library.app_start_record.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "time_trace")
class TimeTraceEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "start_mode")
    var startMode: Int? = null

    @ColumnInfo(name = "trace_date")
    var traceDate: Date? = null

    @ColumnInfo(name = "trace_content")
    var traceContent: String? = null

    @ColumnInfo(name = "desc")
    var desc: String? = null
}