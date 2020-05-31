package com.dragonforest.library.appstartrecord.util

/**
 *
 * create by DragonForest at 2020/5/29
 */
object ClickUtil {

    var TIME_DIVIDE = 1000L
    var clickCount = 0
    var lastClickTimeStrap = 0L

    /**
     * 统计点击次数
     * 使用： 放置在onClick中
     */
    fun clickCount():Int{
        var now = System.currentTimeMillis()
        if((now - lastClickTimeStrap)< TIME_DIVIDE){
            clickCount++
        }else{
            clickCount = 1
        }
        lastClickTimeStrap = System.currentTimeMillis()
        return clickCount
    }

}