package com.dragonforest.library.app_start_record.database

import android.content.Context
import androidx.room.*
import com.dragonforest.library.app_start_record.database.dao.TimeTraceDao
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity
import java.util.*

/***
 *  create by DragonForest on 2020/5/25
 */
@Database(entities = arrayOf(TimeTraceEntity::class), version = 1)
@TypeConverters(Converters::class)
abstract class TimeTraceDatabase : RoomDatabase() {
    abstract fun timeTraceDao(): TimeTraceDao

    companion object {
        const val dbName = "timetrace.db"
        @Volatile
        var instance: TimeTraceDatabase? = null
        fun getDatabase(context: Context): TimeTraceDatabase {
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance =  Room.databaseBuilder(
                            context.applicationContext,
                            TimeTraceDatabase::class.java, dbName
                        ).allowMainThreadQueries().build()
                    }
                }
            }
            return instance!!
        }
    }
}

class Converters {
    @TypeConverter
    fun timeStamp2Date(time: Long?): Date? {
        return if (time == null) null else Date(time)
    }

    @TypeConverter
    fun date2TimeStamp(date: Date?): Long? {
        return date?.time
    }
}