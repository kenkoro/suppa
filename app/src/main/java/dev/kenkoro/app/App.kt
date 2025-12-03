package dev.kenkoro.app

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.icerock.moko.crashreporting.crashlytics.CrashlyticsLogger
import dev.icerock.moko.crashreporting.napier.CrashReportingAntilog
import dev.kenkoro.app.di.DaggerApp
import dev.kenkoro.utils.LogcatAntilog
import io.github.aakira.napier.Napier

internal class App : DaggerApp() {
    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = BuildConfig.DEBUG.not()

        val antilog: LogcatAntilog? = if (BuildConfig.DEBUG) {
            LogcatAntilog()
        } else {
            null
        }

        antilog?.let { Napier.base(antilog = it) }
        Napier.base(CrashReportingAntilog(CrashlyticsLogger()))
    }
}
