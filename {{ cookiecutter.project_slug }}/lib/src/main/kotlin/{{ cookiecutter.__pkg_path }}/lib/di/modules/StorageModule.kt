package {{ cookiecutter.pkg_name }}.lib.di.modules

import dagger.Binds
import dagger.Module
import {{ cookiecutter.pkg_name }}.lib.storage.EncryptedKeyValueStorage
import {{ cookiecutter.pkg_name }}.lib.storage.KeyValueStorage

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
