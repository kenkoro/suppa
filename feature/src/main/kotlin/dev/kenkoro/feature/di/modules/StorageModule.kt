package dev.kenkoro.feature.di.modules

import dagger.Binds
import dagger.Module
import dev.kenkoro.feature.storage.EncryptedKeyValueStorage
import dev.kenkoro.feature.storage.KeyValueStorage

@Module
interface StorageModule {
    // TODO: Bind a feature-specific app storage interface

    @Binds
    fun bindEncryptedKeyValueStorage(
        implementation: EncryptedKeyValueStorage,
    ): EncryptedKeyValueStorage

    @Binds
    fun bindKeyValueStorage(implementation: KeyValueStorage): KeyValueStorage
}
