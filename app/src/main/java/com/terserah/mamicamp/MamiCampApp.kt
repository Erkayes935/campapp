package com.terserah.mamicamp

import android.app.Application
import com.bugsnag.android.Bugsnag
import timber.log.Timber

class MamiCampApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Bugsnag.init(this)
        Timber.plant(object : Timber.DebugTree(){
            override fun createStackElementTag
                        (element: StackTraceElement): String? {
                return "(${element.fileName}" +
                        ":${element.methodName}) MamiCamp log "
            }
        })
    }
}