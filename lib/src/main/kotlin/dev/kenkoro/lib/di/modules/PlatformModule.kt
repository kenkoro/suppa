package dev.kenkoro.lib.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import dagger.Module
import dagger.Provides
import dev.kenkoro.lib.di.qualifiers.AppContext
import dev.kenkoro.lib.di.qualifiers.EncryptedPrefs
import dev.kenkoro.lib.di.qualifiers.EncryptedSettings
import dev.kenkoro.lib.di.qualifiers.NonEncryptedPrefs
import dev.kenkoro.lib.di.qualifiers.NonEncryptedSettings
import dev.kenkoro.lib.di.scopes.MainScope

@Module
object PlatformModule {
    @MainScope
    @Provides
    fun provideMasterKey(@AppContext context: Context): MasterKey {
        @Suppress("DEPRECATION")
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @MainScope
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

    @MainScope
    @Provides
    @NonEncryptedPrefs
    fun provideNonEncryptedSharedPreferences(@AppContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "${context.packageName}_preferences",
            Context.MODE_PRIVATE,
        )
    }

    @MainScope
    @Provides
    @EncryptedSettings
    fun provideEncryptedSettings(@EncryptedPrefs sharedPreferences: SharedPreferences): Settings {
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }

    @MainScope
    @Provides
    @NonEncryptedSettings
    fun provideNonEncryptedSettings(
        @NonEncryptedPrefs sharedPreferences: SharedPreferences,
    ): Settings {
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}
