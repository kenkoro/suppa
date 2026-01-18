package {{ cookiecutter.pkg_name }}.lib.di.modules

import dagger.Module
import dagger.Provides
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.SupabaseKey
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.SupabaseUrl
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

@Module
object SupabaseModule {
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
