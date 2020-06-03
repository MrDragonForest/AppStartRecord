package com.dragonforest.library.appstartrecord.main1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.appstartrecord.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        TraceTimeMonitor.trace(ChainField.CHAIN_SPLASH_ACTIVITY,TagField.ONCREATE,true)
        super.onCreate(savedInstanceState)
        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        Handler(mainLooper).postDelayed(Runnable {
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        },800)
        TraceTimeMonitor.trace(ChainField.CHAIN_SPLASH_ACTIVITY,TagField.ONCREATE_OVER)
    }
}