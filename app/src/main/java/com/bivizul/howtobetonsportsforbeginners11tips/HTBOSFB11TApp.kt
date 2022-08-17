package com.bivizul.howtobetonsportsforbeginners11tips

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.AF_DEV_KEY
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HTBOSFB11TApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //TODO(OneSignal)
//        // Enable verbose OneSignal logging to debug issues if needed.
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
//
//        // OneSignal Initialization
//        OneSignal.initWithContext(this)
//        OneSignal.setAppId(ONESIGNAL_APP_ID)

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)
    }
}
