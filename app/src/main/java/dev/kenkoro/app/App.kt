package dev.kenkoro.app

import dev.kenkoro.app.di.DaggerApp
import dev.kenkoro.utils.LogcatAntilog
import io.github.aakira.napier.Napier

internal class App : DaggerApp() {
    override fun onCreate() {
        super.onCreate()
        val antilog: LogcatAntilog? = if (BuildConfig.DEBUG) {
            LogcatAntilog()
        } else {
            null
        }

        antilog?.let { Napier.base(antilog = it) }
    }
}
