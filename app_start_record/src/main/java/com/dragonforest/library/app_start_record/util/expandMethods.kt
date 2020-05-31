package com.dragonforest.library.app_start_record.util

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by DragonForest at 2020/5/30
 */

/**
 * 日期时间格式化
 */
fun Date.format():String{
    var simpleFormatter = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
    return simpleFormatter.format(this)
}

fun List<Any>.eachLine():String{
    var str:StringBuffer = StringBuffer()
    this.forEach {
        str.append(it)
        str.append("\n")
    }
    return str.toString()
}