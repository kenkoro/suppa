package {{ cookiecutter.pkg_name }}.data.di.module

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import dagger.Module
import dagger.Provides
import {{ cookiecutter.pkg_name }}.data.di.AppScope
import {{ cookiecutter.pkg_name }}.data.di.qualifier.AppContext
import {{ cookiecutter.pkg_name }}.data.keyvalue.KeyValueStorageImpl
import {{ cookiecutter.pkg_name }}.domain.keyvalue.KeyValueStorage

@Module
object PreferenceModule {
    @AppScope
    @Provides
    fun provideSharedPreferences(@AppContext context: Context): SharedPreferences {
        val name = "${context.packageName}.preferences"
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    @AppScope
    @Provides
    fun provideSettings(preferences: SharedPreferences): Settings {
        return SharedPreferencesSettings(delegate = preferences)
    }

    @AppScope
    @Provides
    fun providePreferenceManager(settings: Settings): KeyValueStorage {
        return KeyValueStorageImpl(settings)
    }
}
