package {{ cookiecutter.pkg_name }}.app

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.icerock.moko.crashreporting.crashlytics.CrashlyticsLogger
import dev.icerock.moko.crashreporting.napier.CrashReportingAntilog
import {{ cookiecutter.pkg_name }}.app.di.component.DaggerApp
import {{ cookiecutter.pkg_name }}.app.utils.LogcatAntilog
import {{ cookiecutter.pkg_name }}.data.di.setupExceptionMappers
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
