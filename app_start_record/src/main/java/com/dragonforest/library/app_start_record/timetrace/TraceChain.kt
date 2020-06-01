package com.dragonforest.library.app_start_record.timetrace

import java.util.*

/**
 *
 * create by DragonForest at 2020/5/31
 */
class TraceChain {
    var chainName: String? = null
    var beginTagName: String? = null
    var tagList: LinkedList<TraceTag>? = null

    fun add(tag: TraceTag) {
        if (tagList == null) {
            tagList = LinkedList<TraceTag>()
        }
        tagList?.add(tag)
    }

    fun clear() {
        tagList?.clear()
    }

    fun size(): Int {
        return tagList?.size ?: 0
    }

    fun allTime(): Long {
        tagList?.let {
            if (it.size ?: 0 > 1) {
                return it.last.tagTimeStamp - it.first.tagTimeStamp
            }
        }
        return 0L
    }

    fun filterBeforeBegin(): TraceChain? {
        if (beginTagName == null) {
            return null
        }
        tagList?.let {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                var tag = iterator.next()
                if (tag.tagName != beginTagName) {
                    it.remove(tag)
                } else {
                    break
                }
            }
        }
        if (tagList?.size == 0) {
            return null
        }
        return this
    }
}