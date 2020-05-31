package com.dragonforest.library.appstartrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dragonforest.library.app_start_record.RecordLogsActivity
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.appstartrecord.util.ClickUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        TraceTimeMonitor.trace(ChainField.CHAIN_MAIN_ACTIVITY, TagField.ONCREATE, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TraceTimeMonitor.trace(ChainField.CHAIN_MAIN_ACTIVITY, TagField.ONCREATE)
        TraceTimeMonitor.report(this)

        initView()
    }

    private fun initView() {
        tv_click.setOnClickListener {
            var count = ClickUtil.clickCount()
            if (count == 5) {
                var intent = Intent(this, RecordLogsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}