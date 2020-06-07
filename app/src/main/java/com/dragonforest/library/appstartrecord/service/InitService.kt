package com.dragonforest.library.appstartrecord.service

import android.app.IntentService
import android.content.Intent
import com.dragonforest.library.appstartrecord.MockSDK

/**
 *
 * create by DragonForest at 2020/6/8
 */
class InitService: IntentService("appStartRecord") {
    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call [.stopSelf].
     *
     * @param intent The value passed to [               ][android.content.Context.startService].
     * This may be null if the service is being restarted after
     * its process has gone away; see
     * [android.app.Service.onStartCommand]
     * for details.
     */
    override fun onHandleIntent(intent: Intent?) {
        MockSDK.init(applicationContext)
    }
}