package com.dragonforest.library.app_start_record.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity

@Dao
interface TimeTraceDao {
    @Insert
    fun insert(vararg timeTraces: TimeTraceEntity)

    @Delete
    fun delete(timeTraces: TimeTraceEntity)

    @Query("SELECT * FROM time_trace order by trace_date desc limit 50")
    fun getAll(): List<TimeTraceEntity>
}