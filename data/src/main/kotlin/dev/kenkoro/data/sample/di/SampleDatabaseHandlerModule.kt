package dev.kenkoro.data.sample.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dev.kenkoro.data.di.AppScope
import dev.kenkoro.data.di.qualifier.AppContext
import dev.kenkoro.feature.sample.SampleDatabase

@Module
object SampleDatabaseHandlerModule {
    @AppScope
    @Provides
    fun provideSqlDriver(@AppContext context: Context): SqlDriver {
        return AndroidSqliteDriver(SampleDatabase.Schema, context, SAMPLE_DB_NAME)
    }
}

private const val SAMPLE_DB_NAME = "sample.db"
