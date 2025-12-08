package dev.kenkoro.lib.di.modules

import dagger.Binds
import dagger.Module
import dev.kenkoro.lib.storage.EncryptedKeyValueStorage
import dev.kenkoro.lib.storage.KeyValueStorage

@Module
internal interface StorageModule {
    // TODO: Bind a feature-specific app storage interface

    @Binds
    fun bindEncryptedKeyValueStorage(
        implementation: EncryptedKeyValueStorage,
    ): EncryptedKeyValueStorage

    @Binds
    fun bindKeyValueStorage(implementation: KeyValueStorage): KeyValueStorage
}
