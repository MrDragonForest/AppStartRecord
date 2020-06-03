package com.dragonforest.library.appstartrecord.main2

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewStub
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dragonforest.library.app_start_record.RecordLogsActivity
import com.dragonforest.library.app_start_record.timetrace.ChainField
import com.dragonforest.library.app_start_record.timetrace.TagField
import com.dragonforest.library.app_start_record.timetrace.TraceTimeMonitor
import com.dragonforest.library.app_start_record.util.Logger
import com.dragonforest.library.appstartrecord.R
import com.dragonforest.library.appstartrecord.constrants.MyChainField
import com.dragonforest.library.appstartrecord.constrants.MyTagField
import com.dragonforest.library.appstartrecord.util.ClickUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_optimize_main.*
import java.lang.ref.WeakReference

/**
 * 优化主页
 *      使用fragment显示splash页面，ViewStub装载主布局，当splash显示到屏幕时，再去真正加载主布局
 */
class OptimizeMainActivity : AppCompatActivity() {
    var splashFragment: SplashFragment? = null
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        TraceTimeMonitor.trace(MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY, TagField.ONCREATE, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optimize_main)
        initSplashView()
        window.decorView.post {
            TraceTimeMonitor.trace(
                MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY,
                MyTagField.ONEND_WINDOW_POST
            )
            handler.post(InitMainViewRunnable(this, vs_main))
            handler.postDelayed(RemoveFragmentRunnable(this, splashFragment), 1000)
        }
        TraceTimeMonitor.trace(MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY, TagField.ONCREATE_OVER)
    }

    override fun onResume() {
        super.onResume()
        TraceTimeMonitor.trace(MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY, MyTagField.ON_RESUME)
    }

    private fun initSplashView() {
        val transaction = supportFragmentManager.beginTransaction()
        splashFragment = SplashFragment()
        transaction.add(R.id.fl_splash, splashFragment!!)
        transaction.commit()
    }

    class RemoveFragmentRunnable(var activity: FragmentActivity, var splashFragment: Fragment?) :
        Runnable {
        var activityRef = WeakReference<FragmentActivity>(activity)
        override fun run() {
            if (activityRef == null) return
            splashFragment?.let {
                val transaction1 = activityRef.get()?.supportFragmentManager?.beginTransaction()
                transaction1?.remove(it)
                transaction1?.commit()
            }
        }
    }

    class InitMainViewRunnable(var context: Context, var mainViewStub: ViewStub) : Runnable {
        var contextRef = WeakReference<Context>(context)
        override fun run() {
            TraceTimeMonitor.trace(
                MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY,
                MyTagField.INIT_MAINVIEW
            )
            initMainView()
            TraceTimeMonitor.trace(
                MyChainField.CHAIN_OPTIMIZE_MAINACTIVITY,
                MyTagField.INIT_MAINVIEW_OVER
            )
            contextRef.get()?.let { TraceTimeMonitor.report(it) }
        }

        fun initMainView() {
            contextRef?.let {
                val mainView = mainViewStub.inflate()
                mainView.tv_click.setOnClickListener {
                    var count = ClickUtil.clickCount()
                    if (count == 5) {
                        var intent = Intent(contextRef.get(), RecordLogsActivity::class.java)
                        context.startActivity(intent)
                    }
                }
                mainView.tv_test.setOnClickListener {
                    var count = ClickUtil.clickCount()
                    if (count == 5) {
                        traceTest()
                    }
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
            if (contextRef != null)
                Toast.makeText(contextRef.get(), testMSg, Toast.LENGTH_LONG).show()
            TraceTimeMonitor.chains.remove(CHAIN_NAME)
        }
    }
}