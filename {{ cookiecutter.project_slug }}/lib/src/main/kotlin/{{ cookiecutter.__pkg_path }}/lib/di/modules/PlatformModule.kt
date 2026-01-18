package {{ cookiecutter.pkg_name }}.lib.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import dagger.Module
import dagger.Provides
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.AppContext
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.EncryptedPrefs
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.EncryptedSettings
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.NonEncryptedPrefs
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.NonEncryptedSettings

@Module
object PlatformModule {
    @Provides
    fun provideMasterKey(@AppContext context: Context): MasterKey {
        @Suppress("DEPRECATION")
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @EncryptedPrefs
    @Suppress("DEPRECATION")
    fun provideEncryptedSharedPreferences(
        @AppContext context: Context,
        masterKey: MasterKey,
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "${context.packageName}_preferences",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    @Provides
    @NonEncryptedPrefs
    fun provideNonEncryptedSharedPreferences(@AppContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "${context.packageName}_preferences",
            Context.MODE_PRIVATE,
        )
    }

    @Provides
    @EncryptedSettings
    fun provideEncryptedSettings(@EncryptedPrefs sharedPreferences: SharedPreferences): Settings {
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }

    @Provides
    @NonEncryptedSettings
    fun provideNonEncryptedSettings(
        @NonEncryptedPrefs sharedPreferences: SharedPreferences,
    ): Settings {
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}
