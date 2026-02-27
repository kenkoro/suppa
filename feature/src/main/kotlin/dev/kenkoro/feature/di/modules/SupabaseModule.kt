package dev.kenkoro.feature.di.modules

import dagger.Module
import dagger.Provides
import dev.kenkoro.feature.di.qualifiers.SupabaseKey
import dev.kenkoro.feature.di.qualifiers.SupabaseUrl
import dev.kenkoro.feature.di.scopes.MainScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

@Module
object SupabaseModule {
    @MainScope
    @Provides
    fun provideSupabaseClient(
        @SupabaseUrl supabaseUrl: String,
        @SupabaseKey supabaseKey: String,
    ): SupabaseClient {
        return createSupabaseClient(supabaseUrl, supabaseKey) {
            install(Postgrest)
        }
    }

    @MainScope
    @Provides
    fun bindSupabaseDatabase(client: SupabaseClient): Postgrest = client.postgrest
}
