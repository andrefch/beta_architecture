package br.com.andrefch.libraries.logger.tree

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

internal class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if ((priority != Log.WARN) && (priority != Log.ERROR)) {
            return
        }

        if (t != null) {
            Crashlytics.logException(t)
        } else {
            Crashlytics.log(priority, tag, message)
        }
    }
}