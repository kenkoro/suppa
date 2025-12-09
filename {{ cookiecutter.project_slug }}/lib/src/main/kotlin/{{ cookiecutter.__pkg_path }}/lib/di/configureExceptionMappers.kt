package {{ cookiecutter.pkg_name }}.lib.di

import dev.icerock.moko.errors.mappers.ExceptionMappersStorage
import dev.icerock.moko.network.errors.registerAllNetworkMappers
import io.github.aakira.napier.Napier

fun configureExceptionMappers() {
    ExceptionMappersStorage
        .registerAllNetworkMappers()
        .onEach { e, _, _ -> Napier.e("New exception was mapped", e) }
}
