package dev.kenkoro.app

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.icerock.moko.crashreporting.crashlytics.CrashlyticsLogger
import dev.icerock.moko.crashreporting.napier.CrashReportingAntilog
import dev.kenkoro.app.di.component.DaggerApp
import dev.kenkoro.app.utils.LogcatAntilog
import dev.kenkoro.data.di.setupExceptionMappers
import io.github.aakira.napier.Napier

class App : DaggerApp() {
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
        setupExceptionMappers()
    }
}
