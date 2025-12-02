package dev.kenkoro.lib.di.modules

import dagger.Module
import dagger.Provides
import dev.kenkoro.lib.di.qualifiers.SupabaseKey
import dev.kenkoro.lib.di.qualifiers.SupabaseUrl
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

@Module
internal object SupabaseModule {
    @Provides
    fun provideSupabaseClient(
        @SupabaseUrl supabaseUrl: String,
        @SupabaseKey supabaseKey: String,
    ): SupabaseClient {
        return createSupabaseClient(supabaseUrl, supabaseKey) {
            install(Postgrest)
        }
    }

    @Provides
    fun bindSupabaseDatabase(client: SupabaseClient): Postgrest = client.postgrest
}
