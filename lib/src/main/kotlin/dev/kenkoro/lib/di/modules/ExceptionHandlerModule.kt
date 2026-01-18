package dev.kenkoro.lib.di.modules

import dagger.Module
import dagger.Provides
import dev.icerock.moko.errors.handler.ExceptionHandler
import dev.icerock.moko.errors.mappers.ExceptionMappersStorage
import dev.icerock.moko.errors.presenters.AlertErrorPresenter
import dev.icerock.moko.errors.presenters.SelectorErrorPresenter
import io.github.aakira.napier.Napier

@Module
object ExceptionHandlerModule {
    @Provides
    fun provideAlertErrorPresenter(): AlertErrorPresenter = AlertErrorPresenter()

    @Provides
    fun provideExceptionHandler(
        alertErrorPresenter: AlertErrorPresenter,
    ): ExceptionHandler {
        return ExceptionHandler(
            exceptionMapper = ExceptionMappersStorage.throwableMapper(),
            errorPresenter = SelectorErrorPresenter { throwable ->
                alertErrorPresenter
            },
            onCatch = { e -> Napier.e("New error was caught", e) }
        )
    }
}
