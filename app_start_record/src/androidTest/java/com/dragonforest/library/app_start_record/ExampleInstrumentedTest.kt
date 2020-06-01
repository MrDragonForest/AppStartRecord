package com.dragonforest.library.app_start_record

import android.widget.Toast
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.app_start_record.util.Logger

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dragonforest.library.app_start_record.test", appContext.packageName)
    }

    val CHAIN_NAME = "TestChainName"
    var COUNTS = 100

    @Before
    fun begin(){
        TraceTimeMonitor.trace(CHAIN_NAME,"begin",true)
    }

    @Test
    fun traceTest(){
        var start = System.currentTimeMillis()
        for(i in 0..COUNTS) {
            TraceTimeMonitor.trace(CHAIN_NAME, "tag$i")
        }
        var end = System.currentTimeMillis()
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        println("trace $COUNTS counts time ->${(end-start)}ms")
        Logger.i("TraceTest","trace $COUNTS counts time ->${(end-start)}ms")
    }
}