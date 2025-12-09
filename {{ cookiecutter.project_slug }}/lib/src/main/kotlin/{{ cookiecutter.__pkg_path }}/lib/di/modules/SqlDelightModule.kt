package {{ cookiecutter.pkg_name }}.lib.di.modules

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.AppContext
import {{ cookiecutter.pkg_name }}.lib.sample.SampleDatabase

@Module
internal object SqlDelightModule {
    @Provides
    fun provideSqlDriver(@AppContext context: Context): SqlDriver {
        return AndroidSqliteDriver(SampleDatabase.Schema, context, SAMPLE_DB_NAME)
    }
}

private const val SAMPLE_DB_NAME = "sample.db"
