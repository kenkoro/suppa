package dev.kenkoro.data.keyvalue

import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableString
import dev.kenkoro.domain.keyvalue.KeyValueStorage
import javax.inject.Inject

class KeyValueStorageImpl @Inject constructor(
    settings: Settings,
) : KeyValueStorage {
    override var owner: String? by settings.nullableString(OWNER_KEY)

    companion object {
        private const val OWNER_KEY = "ownerKey"
    }
}
