package com.dragonforest.library.app_start_record.util

import android.util.Log

/**
 *
 * create by DragonForest at 2020/5/4
 */
object Logger {
    var debug = true
    fun i(tag: String, msg: String) {
        if (debug) {
            Log.i(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (debug) {
            Log.e(tag, msg)
        }
    }
}