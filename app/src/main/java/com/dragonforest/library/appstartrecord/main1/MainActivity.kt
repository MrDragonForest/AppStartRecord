package com.dragonforest.library.appstartrecord.main1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dragonforest.library.app_start_record.RecordLogsActivity
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.app_start_record.util.Logger
import com.dragonforest.library.appstartrecord.R
import com.dragonforest.library.appstartrecord.util.ClickUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        TraceTimeMonitor.trace(ChainField.CHAIN_MAIN_ACTIVITY, TagField.ONCREATE, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        TraceTimeMonitor.trace(ChainField.CHAIN_MAIN_ACTIVITY, TagField.ONCREATE_OVER)
        TraceTimeMonitor.report(this)
    }

    private fun initView() {
        tv_click.setOnClickListener {
            var count = ClickUtil.clickCount()
            if (count == 5) {
                var intent = Intent(this, RecordLogsActivity::class.java)
                startActivity(intent)
            }
        }

        tv_test.setOnClickListener {
            var count = ClickUtil.clickCount()
            if (count == 5) {
                traceTest()
            }
        }
    }

    fun traceTest() {
        val CHAIN_NAME = "TestChainName"
        var COUNTS = 100000
        var start = System.currentTimeMillis()
        for (i in 0..COUNTS) {
            TraceTimeMonitor.trace(CHAIN_NAME, "tag$i")
        }
        var end = System.currentTimeMillis()
        var testMSg = "trace $COUNTS counts time ->${(end - start)}ms"
        Logger.i("TraceTest", testMSg)
        Toast.makeText(this, testMSg, Toast.LENGTH_LONG).show()
        TraceTimeMonitor.chains.remove(CHAIN_NAME)
    }
}