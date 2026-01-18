package dev.kenkoro.lib.di.modules

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dev.kenkoro.lib.di.qualifiers.AppContext
import dev.kenkoro.lib.sample.SampleDatabase

@Module
object SqlDelightModule {
    @Provides
    fun provideSqlDriver(@AppContext context: Context): SqlDriver {
        return AndroidSqliteDriver(SampleDatabase.Schema, context, SAMPLE_DB_NAME)
    }
}

private const val SAMPLE_DB_NAME = "sample.db"
