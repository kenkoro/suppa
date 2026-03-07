package dev.kenkoro.data.di.module

import dagger.Module
import dagger.Provides
import dev.kenkoro.data.di.AppScope
import dev.kenkoro.data.di.qualifier.SupabaseKey
import dev.kenkoro.data.di.qualifier.SupabaseUrl
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

@Module
object SupabaseModule {
    @AppScope
    @Provides
    fun provideSupabaseClient(
        @SupabaseUrl supabaseUrl: String,
        @SupabaseKey supabaseKey: String,
    ): SupabaseClient {
        return createSupabaseClient(supabaseUrl, supabaseKey) {
            install(Postgrest)
        }
    }

    @AppScope
    @Provides
    fun bindSupabaseDatabase(client: SupabaseClient): Postgrest = client.postgrest
}
